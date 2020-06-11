package mips.converter.memory;

import java.nio.ByteBuffer;

public class UtilsMemory {
	public static int loadHex(String s) throws NumberFormatException {
		int full = 0;
		for(int i = 0; i < s.length(); i++) {
			int t = Integer.parseInt(s.charAt(i) + "", 16);
			full = full << 4;
			full = full | t;
		}
		return full;
    }
    
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
  
	public static String formatHex(long n, int amount) {
		String s = Long.toHexString(n);
		while(s.length() < amount) {
		  s = "0"+s;
		}
		return s;		
	}

    public static String printMemory(MemoryBlock m) {
		return new String ("0x"+UtilsMemory.formatHex(UtilsMemory.bytesToLong(m.getName()), 3)
		+" 0x"+UtilsMemory.formatHex(UtilsMemory.bytesToLong(m.getValue()), 8));
	}
}