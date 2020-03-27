package mips.converter.converters;

/**
 * RegisterConverter
 */
public class RegisterConverter {

	// public String convertTypeR(String instruction) {

    // }
    
	// public String convertTypeJ(String instruction) {
		
    // }

	// public String convertTypeI(String instruction) {
		
    // }

    public int getRegisterVariable(String variable, String value) {
        if (
            (variable.equals("s") && value.equals("p") == true) || 
            (variable.equals("t") && value.equals("8") == true) || 
            (variable.equals("t") && value.equals("9") == true) || 
            (variable.equals("a") && value.equals("t") == true)
        ) {
            variable.concat(value);
        }

        switch (variable) {
            case "sp":
                return 29;
            case "t8":
                return 24;
            case "t9":
                return 25;
            case "at":
                return 1;
            case "s":
                return Integer.parseInt(value) + 15;
            case "t":
                return Integer.parseInt(value) + 7;
            case "a":
                return Integer.parseInt(value) + 3;
            case "z":
                return 0;
            case "v":
                return Integer.parseInt(value) + 1;
            case "k":
                return Integer.parseInt(value) + 24;
            case "g":
                return 28;
            case "f":
                return 30;
            case "r":
                return 31;
            default:
                return 0;
        }
    }
}