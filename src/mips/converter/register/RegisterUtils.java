package mips.converter.register;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class RegisterUtils {

    public static final Map<String, Integer> REGISTERS_MAP = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
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

      public static final Map<String, String> REGISTERS_OPCODE = new HashMap<String, String>() {

        private static final long serialVersionUID = 1L;
    
        {
          put("zero", "00000");
          put("at", "00001");
          put("v0", "00010");
          put("v1", "00011");
          put("a0", "00100");
          put("a1", "00101");
          put("a2", "00110");
          put("a3", "00111");
          put("t0", "01000");
          put("t1", "01001");
          put("t2", "01010");
          put("t3", "01011");
          put("t4", "01100");
          put("t5", "01101");
          put("t6", "01110");
          put("t7", "01111");
          put("t8", "11000");
          put("t9", "11001");
          put("s0", "10000");
          put("s1", "10001");
          put("s2", "10010");
          put("s3", "10011");
          put("s4", "10100");
          put("s5", "10101");
          put("s6", "10110");
          put("s7", "10111");
          put("k0", "11010");
          put("k1", "11011");
          put("gp", "11100");
          put("sp", "11101");
          put("fp", "11110");
          put("ra", "11111");
          put("0", "00000");
        }
      };


    public static byte[] longToBytes(long x) throws java.nio.BufferUnderflowException {
      ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
      buffer.putLong(x);
      return buffer.array();
    }
    
    public static long bytesToLong(byte[] bytes) throws java.nio.BufferUnderflowException {
      ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
      buffer.put(bytes);
      buffer.flip();
      return buffer.getLong();
    }

    public static String formatHex(long n) {
      String s = Long.toHexString(n);
      while(s.length() < 8) {
        s = "0"+s;
      }
      return s;		
    }

    public static String printRegister(Register r) {
      return new String ("$"+r.getName()+" 0x"+formatHex(bytesToLong(r.getValue())));
    }
}