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

  private static String decodeTypeR(String[] operandsBits) {
    String result = "";

    for (String string : operandsBits) {

    }

    return result;
  }

  private static String decodeTypeJ(String[] operandsBits) {
    String result = "";
    System.out.println(operandsBits.toString());

    for (String operand : operandsBits) {
      String unpaddedImmediate = Integer.toBinaryString(Integer.parseInt(operand));
      result += "0000000000000000000000000".substring(unpaddedImmediate.length()) + unpaddedImmediate;
    }

    return result;
  }

  private static String decodeTypeI(String[] operandsBits) {
    String result = "";

    for (String operand : operandsBits) {
      Boolean isAbsolute = isAbsoluteRegister(operand);

      if (!isAbsolute) {
        if (operand.contains("(")) {
          String[] splittedOperand = operand.split("[\\(\\)]");
          String register = splittedOperand[1];
          String immediate = splittedOperand[0];

          Integer registerInt = getRegisterInt(register);
          String unpaddedRegister = Integer.toBinaryString(registerInt);
          register = "00000".substring(unpaddedRegister.length()) + unpaddedRegister;

          String unpaddedImmediate = Integer.toBinaryString(Integer.parseInt(immediate));
          result = register + result + "0000000000000000".substring(unpaddedImmediate.length()) + unpaddedImmediate;
          continue;
        }

        Integer registerInt = getRegisterInt(operand);
        String unpaddedRegister = Integer.toBinaryString(registerInt);
        result += "00000".substring(unpaddedRegister.length()) + unpaddedRegister;
        continue;
      }

      String unpaddedImmediate = Integer.toBinaryString(Integer.parseInt(operand));
      result += "0000000000000000".substring(unpaddedImmediate.length()) + unpaddedImmediate;
    }

    System.out.println(result);

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