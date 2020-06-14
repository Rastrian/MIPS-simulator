package mips.converter.instructions;

import mips.converter.memory.Memory;
import mips.converter.register.RegisterMain;

public class BinaryUtils {

    public static String processInstruction(String instruction) throws Exception {
        try {
            String[] bits = instruction.replace("$", "").replace("(", " ").split(" ", -1);
            String opcode = bits[0];
            String instructionType = InstructionHelpers.getRegisterType(opcode);
            String[] operands = instruction.replace(opcode, "").replace(" ", "").split(",");

            if (opcode.equals("lw") || opcode.equals("sw")){
                bits[0] = bits[0].replace(" ", "");
                bits[1] = bits[1].replace(")", "");
                bits[1] = bits[1].replace(",", "");
                Memory.setIndexAddress(Opcode.MAP_OPCODE.get(bits[0])+RegisterMain.getRegisterBinary(bits[1]));
            }

            return getBinary(instructionType, opcode, operands);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getBinary(String instructionType, String opCode, String[] operands) {
        return Opcode.MAP_OPCODE.get(opCode) + DecodeOperands.decode(instructionType, opCode, operands);
    }
}