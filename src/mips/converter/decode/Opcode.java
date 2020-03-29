package mips.converter.decode;

import java.util.HashMap;
import java.util.Map;

public class Opcode {
  public static final Map<String, String> MAP = new HashMap<String, String>() {
    {
      put("add", "000000");
      put("addu", "000000");
      put("addi", "001000");
      put("sub", "000000");
      put("subu", "000000");
      put("mult", "000000");
      put("div", "000000");
      put("neg", "000000");
      put("and", "000000");
      put("andi", "001100");
      put("or", "000000");
      put("ori", "001101");
      put("xor", "000000");
      put("nor", "000000");
      put("slt", "000000");
      put("sltu", "000000");
      put("slti", "001010");
      put("sll", "000000");
      put("srl", "000000");
      put("lw", "100011");
      put("sw", "101011");
      put("beq", "000100");
      put("bne", "000101");
      put("j", "000010");
      put("jr", "000000");
      put("jal", "000011");
    }
  };
}