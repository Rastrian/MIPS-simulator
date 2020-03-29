package mips.converter.decode;

import java.util.HashMap;
import java.util.Map;

public class DecodeOperands {
  public static final Map<String, Integer> REGISTERS_MAP = new HashMap<String, Integer>() {
    {
      put("zero", 0);
      put("at", 1);
      put("-v", 2);
      put("-a", 4);
      put("-t", 8);
      put("-r", 16);
      put("-s", 16);
      put("t8", 24);
      put("t9", 25);
      put("-k", 26);
      put("gp", 28);
      put("sp", 29);
      put("fp", 30);
      put("ra", 31);
    }
  };

  public static String decode(String operationType, String[] operands) {
    String lcOperationType = operationType.toLowerCase();

    if (lcOperationType.equals("r")) {
      return decodeTypeR(operands);
    } else if (lcOperationType.equals("j")) {
      return decodeTypeJ(operands);
    } else if (lcOperationType.equals("i")) {
      return decodeTypeI(operands);
    }

    return "";
  }

  private static String getDecodedRegister(String operationType, String register) {
    if (operationType.equals("i")) {
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
    }

    return gap.substring(unpaddedImmediate.length()) + unpaddedImmediate;
  }

  private static String decodeTypeR(String[] operandsBits) {
    String result = "";

    for (String string : operandsBits) {

    }

    return result;
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
    Integer value = REGISTERS_MAP.get(register);

    if (value == null) {
      value = REGISTERS_MAP.get("-" + register.substring(0, 1)) + Integer.parseInt(register.substring(1));
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