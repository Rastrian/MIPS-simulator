package mips.converter.helpers;

import java.util.Arrays;

/**
 * InstructionHelpers
 */
public class InstructionHelpers {

    public static String getRegisterType(String instruction) throws Exception {
        
        String[] typeRList = { "add", "sub", "mult", "div", "xor", "nor", "slt", "sll", "and", "srl", "jr", "or",
                "neg" };

        String[] typeJList = { "j", "jal" };

        String[] typeIList = { "addi", "lw", "sw", "beq", "bne", "slti", "and", "ori" };

        if (Arrays.asList(typeRList).contains(instruction)) {
            System.out.println("Entrou R");
            return "r";
        } else if (Arrays.asList(typeJList).contains(instruction)) {
            System.out.println("Entrou J");
            return "j";
        } else if (Arrays.asList(typeIList).contains(instruction)) {
            System.out.println("Entrou I");
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
}