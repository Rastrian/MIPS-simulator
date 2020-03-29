package mips.converter.decode;

import java.util.HashMap;
import java.util.Map;

public class Opcode {
  public static final Map<String, String> MAP = new HashMap<String, String>() {
    {
      put("add", "100000");
      put("addi", "001000");
      put("sub", "100010");
      put("mul", "011000");
      put("div", "011010");
      put("neg", "000000");
      put("and", "100100");
      put("andi", "001100");
      put("or", "100101");
      put("ori", "001101");
      put("xor", "100110");
      put("nor", "100111");
      put("slt", "101010");
      put("slti", "001010");
      put("sll", "000000");
      put("srl", "000010");
      put("lw", "100011");
      put("sw", "101011");
      put("beq", "000100");
      put("bne", "000101");
      put("j", "000010");
      put("jr", "001000");
      put("jal", "000011");
    }
  };
}