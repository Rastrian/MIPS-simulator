package mips.converter.instructions;

import java.util.HashMap;
import java.util.Map;

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

    if (lcOperationType.equals("r")) {
      return decodeTypeR(opCode, operands);
    } else if (lcOperationType.equals("j")) {
      return decodeTypeJ(operands);
    } else if (lcOperationType.equals("i")) {
      return decodeTypeI(operands, opCode);
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
      RegisterMain.updateRegister("s"+Integer.toString(i), Long.parseLong(operandsBitsInBinary[i]));
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

  private static String decodeTypeI(String[] operandsBits, String Opcode) {
    String result = "";
    Register temp = null; Register updated = null; long result_reg = 0;

    for (int i = 0; i < operandsBits.length; i++) {
      String operand = operandsBits[i];
      Boolean isAbsolute = isAbsoluteRegister(operand);

      if (!isAbsolute) {
        if (operand.contains("(")) {
          String[] splittedOperand = operand.split("[\\(\\)]");
          String register = getDecodedRegister("i", splittedOperand[1]);
          String immediate = getDecodedImmediate("i", splittedOperand[0]);

          result = register + result + immediate;
          switch(Opcode){
            case "andi":
              updated = RegisterMain.getRegister("s0");
              temp = RegisterMain.getRegister("s1");
      
              result_reg = (temp.getBaseValue() & Long.parseLong(immediate));
              RegisterMain.updateRegister(updated.getName(), result_reg);
              break;
            case "ori":
              updated = RegisterMain.getRegister("s0");
              temp = RegisterMain.getRegister("s1");
  
              result_reg = (temp.getBaseValue() & Long.parseLong(immediate));
              RegisterMain.updateRegister(updated.getName(), result_reg);
              break;
            case "slti":
                updated = RegisterMain.getRegister("s0");
                temp = RegisterMain.getRegister("s1");

                result_reg = 0;
                if (temp.getBaseValue() < Long.parseLong(immediate))
                  result_reg = 1;

                RegisterMain.updateRegister(updated.getName(), result_reg);
                break;
            case "addi":
                updated = RegisterMain.getRegister("s0");
                temp = RegisterMain.getRegister("s1");
                result_reg = temp.getBaseValue() + Long.parseLong(immediate);
                RegisterMain.updateRegister(updated.getName(), result_reg);
                break;
          }
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