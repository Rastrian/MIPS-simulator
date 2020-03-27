package mips.converter.helpers;

import java.util.Arrays;
import java.util.List;

/**
 * InstructionHelpers
 */
public class InstructionHelpers {

    public static String getRegisterType(String instruction) throws Exception {

        String[] typeRList = { "add", "sub", "mul", "div", "xor", "nor", "slt", "sll", "and", "srl", "jr", "or",
                "neg" };

        String[] typeJList = { "j", "jal" };

        String[] typeIList = { "addi", "lw", "sw", "beq", "bne", "slti", "and", "ori" };

        if (Arrays.asList(typeRList).contains(instruction)) {
            return "r";
        } else if (Arrays.asList(typeJList).contains(instruction)) {
            return "j";
        } else if (Arrays.asList(typeIList).contains(instruction)) {
            return "i";
        } else {
            throw new Exception("Instruction does not match any type");
        }
    }

    public static String formatInstruction(String instruction) {
        return instruction.replace(" ", "").replace(",", "").replace("(zero)", "z0");
    }

    public static String getOpcode(String instruction) {
        return instruction.substring(0, instruction.indexOf("$"));
    }

    public String binaryListDecoding(List<String> valuesList, int index, int amount) {
        int value = Integer.parseInt(valuesList.get(index));

        int[] binaryList = new int[amount];

        for (int i = 0; i <= (amount - 1); i++) {
            binaryList[i] = value % 2;
            value = value / 2;
        }

        String bynaryLine = "";

        for (int i = (amount - 1); i >= 0; i--) {
            bynaryLine += ((Integer.toString(binaryList[i])));
        }

        return bynaryLine;
    }
}