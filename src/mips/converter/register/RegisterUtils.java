package mips.converter.register;

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
}