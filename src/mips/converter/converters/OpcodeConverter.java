package mips.converter.converters;

public class OpcodeConverter {

    public String getBinary(String opcode) {
        switch (opcode) {
            case "add":
                return "100000";
            case "addi":
                return "001000";
            case "sub":
                return "100010";
            case "mul":
                return "011000";
            case "div":
                return "011010";
            case "and":
                return "100100";
            case "andi":
                return "001100";
            case "or":
                return "100101";
            case "ori":
                return "001101";
            case "xor":
                return "100110";
            case "nor":
                return "100111";
            case "slt":
                return "101010";
            case "slti":
                return "001010";
            case "sll":
                return "000000";
            case "srl":
                return "000010";
            case "lw":
                return "100011";
            case "sw":
                return "101011";
            case "beq":
                return "000100";
            case "bne":
                return "000101";
            case "j":
                return "000010";
            case "jr":
                return "001000";
            case "jal":
                return "000011";
        }
        return null;
    }
}
