package mips.converter.memory;

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
    
	public static String formatHex(int n, int count) {
		String s = Integer.toHexString(n).toUpperCase();
		while(s.length() < 8) {
            s = "0x0" + s + " 0x00000000";
            if (count <= 15){
                s = "0x" + s + " 0x00000000";
            }
		}
		return s;
	}
}