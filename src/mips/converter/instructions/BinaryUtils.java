package mips.converter.instructions;

public class BinaryUtils {

    public static String processInstruction(String instruction) throws Exception {
        try {
            String[] bits = instruction.replace("$", "").split(" ");
            String opcode = bits[0];
            String instructionType = InstructionHelpers.getRegisterType(opcode);
            String[] operands = instruction.replace(opcode, "").replace(" ", "").split(",");

            return getBinary(instructionType, opcode, operands);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getBinary(String instructionType, String opCode, String[] operands) {
        return Opcode.MAP_OPCODE.get(opCode) + DecodeOperands.decode(instructionType, opCode, operands);
    }
}