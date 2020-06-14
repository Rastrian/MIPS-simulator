package mips.converter.instructions;

import java.util.HashMap;
import java.util.Map;

import mips.converter.memory.Memory;
import mips.converter.memory.MemoryBlock;
import mips.converter.register.Register;
import mips.converter.register.RegisterMain;
import mips.converter.register.RegisterUtils;

public class DecodeOperands {

  public static final Map<String, String> FUNCT_MAP = new HashMap<String, String>() {
    private static final long serialVersionUID = 1L;
    {
      put("mult", "24");
      put("div", "26");
      put("add", "32");
      put("addu", "33");
      put("sub", "34");
      put("subu", "35");
      put("and", "36");
      put("or", "37");
      put("xor", "38");
      put("nor", "39");
      put("slt", "42");
      put("sltu", "43");
      put("sll", "0");
      put("srl", "2");
      put("jr", "8");
    }
  };

  public static String decode(String operationType, String opCode, String[] operands) {
    String lcOperationType = operationType.toLowerCase();

    Register temp = null; Register temp2 = null;
    Register updated = null; long result = 0; long resultLo = 0; long resultHi = 0;
    System.out.println(opCode);
    switch(opCode){
        case "add":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);
            temp2 = RegisterMain.getRegisterByIndex(2);
            result = temp.getBaseValue() + temp2.getBaseValue();
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "addi":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(2);
            result = temp.getBaseValue() + Long.parseLong(getDecodedImmediate("i", opCode));
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "sub":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);
            temp2 = RegisterMain.getRegisterByIndex(2);
            result = temp.getBaseValue() - temp.getBaseValue();
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "mult":
            temp = RegisterMain.getRegisterByIndex(0);
            temp2 = RegisterMain.getRegisterByIndex(1);
            if ((temp.getBaseValue() * temp2.getBaseValue()) > 4294967299.){
              resultLo = (temp.getBaseValue() * temp2.getBaseValue()) / 2;
              resultHi = resultLo;

              RegisterMain.updateRegister("hi", resultHi);
              RegisterMain.updateRegister("lo", resultLo);
            }else{
              resultLo = temp.getBaseValue() * temp2.getBaseValue();
              RegisterMain.updateRegister("lo", resultLo);
            }
            break;
        case "div":
            temp = RegisterMain.getRegisterByIndex(0);
            temp2 = RegisterMain.getRegisterByIndex(1);

            resultLo = temp.getBaseValue() / temp2.getBaseValue();
            resultHi = temp.getBaseValue() % temp.getBaseValue();

            RegisterMain.updateRegister("hi", resultHi);
            RegisterMain.updateRegister("lo", resultLo);
            break;
        case "neg":
            temp = RegisterMain.getRegisterByIndex(1);
            RegisterMain.updateRegister(temp.getName(), -temp.getBaseValue());
            break;
        case "slt":
            temp = RegisterMain.getRegisterByIndex(1);
            temp2 = RegisterMain.getRegisterByIndex(2);

            result = 0;
            if (temp.getBaseValue() < temp2.getBaseValue())
              result = 1;

            RegisterMain.updateRegister(temp.getName(), result);
            break;
        case "slti":
            temp = RegisterMain.getRegisterByIndex(1);

            result = 0;
            if (temp.getBaseValue() < Long.parseLong(getDecodedImmediate("i", opCode)))
              result = 1;

            RegisterMain.updateRegister(temp.getName(), result);
            break;
        case "lw":
            MemoryBlock mem = Memory.getIndexAddress();
            temp = RegisterMain.getRegisterByIndex(0);
            RegisterMain.updateRegister(temp.getName(), mem.getValueToLong());
            break;
        case "sw":
            Memory.setIndex(Integer.parseInt(Memory.getIndexAddress().toString()));
            Memory.setIndexAddress(RegisterMain.getRegisterByIndex(0).getBaseValue().toString());
            break;
        case "and":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);
            temp2 = RegisterMain.getRegisterByIndex(2);
            result = (temp.getBaseValue() & temp2.getBaseValue());
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "andi":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);

            result = (temp.getBaseValue() & Long.parseLong(getDecodedImmediate("i", opCode)));
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "or":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);
            temp2 = RegisterMain.getRegisterByIndex(2);
            result = (temp.getBaseValue() | temp2.getBaseValue());
            RegisterMain.updateRegister(updated.getName(), result);
            break;
        case "ori":
            updated = RegisterMain.getRegisterByIndex(0);
            temp = RegisterMain.getRegisterByIndex(1);

            result = (temp.getBaseValue() & Long.parseLong(getDecodedImmediate("i", opCode)));
            RegisterMain.updateRegister(updated.getName(), result);
            break;
    }

    if (lcOperationType.equals("r")) {
      return decodeTypeR(opCode, operands);
    } else if (lcOperationType.equals("j")) {
      return decodeTypeJ(operands);
    } else if (lcOperationType.equals("i")) {
      return decodeTypeI(operands);
    }

    return "";
  }

  private static String getDecodedRegister(String operationType, String register) {
    if (operationType.equals("i") || operationType.equals("r")) {
      Integer registerInt = getRegisterInt(register);
      String unpaddedRegister = Integer.toBinaryString(registerInt);

      return "00000".substring(unpaddedRegister.length()) + unpaddedRegister;
    }

    return "";
  }

  private static String getDecodedImmediate(String operationType, String immediate) {
    String unpaddedImmediate = Integer.toBinaryString(Integer.parseInt(immediate));
    String gap = "";

    if (operationType.equals("i")) {
      gap = "0000000000000000";
    } else if (operationType.equals("j")) {
      gap = "0000000000000000000000000";
    } else if (operationType.equals("r-1")) {
      gap = "00000";
    } else if (operationType.equals("r-2")) {
      gap = "000000";
    }

    return gap.substring(unpaddedImmediate.length()) + unpaddedImmediate;
  }

  private static String getShamt(String opCode) {
    return getDecodedImmediate("r-1", "0");
  }

  private static String getFunct(String opCode) {
    return getDecodedImmediate("r-2", FUNCT_MAP.get(opCode));
  }

  private static String decodeTypeR(String opCode, String[] operandsBits) {
    String[] operandsBitsInBinary = { "00000", "00000", "00000" };

    String result = "";
    for (int i = 0; i < operandsBits.length; i++) {
      String operand = operandsBits[i];
      Boolean isAbsolute = isAbsoluteRegister(operand);

      operandsBitsInBinary[i] = isAbsolute ? getDecodedImmediate("r-1", operand) : getDecodedRegister("r", operand);
    }

    String operandsOrdered = getOrderedOperandsForTypeR(opCode, operandsBitsInBinary);
    result = getResultTypeR(operandsOrdered, opCode);

    return result;
  }

  private static String getResultTypeR(String operandsOrdered, String opCode) {
    if (opCode.equals("sll") || opCode.equals("srl")) {
      return getShamt(opCode) + operandsOrdered + getFunct(opCode);
    }
    return operandsOrdered + getShamt(opCode) + getFunct(opCode);
  }

  private static String getOrderedOperandsForTypeR(String opCode, String[] operandsBitsInBinary) {
    if (opCode.equals("div") || opCode.equals("mult")) {
      return operandsBitsInBinary[0] + operandsBitsInBinary[1] + operandsBitsInBinary[2];
    } else if (opCode.equals("sll") || opCode.equals("srl")) {
      return operandsBitsInBinary[1] + operandsBitsInBinary[0] + operandsBitsInBinary[2];
    } else if (opCode.equals("jr")) {
      return operandsBitsInBinary[0] + operandsBitsInBinary[1] + operandsBitsInBinary[2];
    }
    return operandsBitsInBinary[1] + operandsBitsInBinary[2] + operandsBitsInBinary[0];
  }

  private static String decodeTypeJ(String[] operandsBits) {
    String result = "";

    for (String operand : operandsBits) {
      result += getDecodedImmediate("j", operand);
    }

    return result;
  }

  private static String decodeTypeI(String[] operandsBits) {
    String result = "";

    for (int i = 0; i < operandsBits.length; i++) {
      String operand = operandsBits[i];
      Boolean isAbsolute = isAbsoluteRegister(operand);

      if (!isAbsolute) {
        if (operand.contains("(")) {
          String[] splittedOperand = operand.split("[\\(\\)]");
          String register = getDecodedRegister("i", splittedOperand[1]);
          String immediate = getDecodedImmediate("i", splittedOperand[0]);

          result = register + result + immediate;
          continue;
        } else if (i == 1) {
          result = getDecodedRegister("i", operand) + result;
          continue;
        }

        result += getDecodedRegister("i", operand);
        continue;
      }

      result += getDecodedImmediate("i", operand);
    }

    return result;
  }

  private static Integer getRegisterInt(String register) {
    Integer value = RegisterUtils.REGISTERS_MAP.get(register);

    if (value == null) {
      value = RegisterUtils.REGISTERS_MAP.get("-" + register.substring(0, 1)) + Integer.parseInt(register.substring(1));
    }

    return value;
  }

  private static Boolean isAbsoluteRegister(String operand) {
    try {
      return operand.equals(String.valueOf(Integer.parseInt(operand)));
    } catch (Exception e) {
      return false;
    }
  }
}