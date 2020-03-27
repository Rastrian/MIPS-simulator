package mips.converter.invoke;

import java.util.List;

import mips.converter.helpers.InstructionHelpers;
import mips.converter.io.ReadFile;
import mips.converter.io.WriteFile;

/**
 * MIPSConverter
 */
public class MIPSConverter {
    public static void run(String inputPath, String outputPath) throws Exception {
        ReadFile readFile = new ReadFile();
        WriteFile writeFile = new WriteFile();

        try {
            List<String> commands = readFile.getCommands(inputPath);

            for (int i = 0; i < commands.size(); i++) {
                String decoded = " ";

                if (commands.get(i).equals("nop")) {
                    writeFile.writeBinary(outputPath, decoded);
                } else {
                    decoded = processInstruction(commands.get(i));
                    writeFile.writeBinary(outputPath, decoded);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static String processInstruction(String instruction) throws Exception {
        try {
            String opcode = InstructionHelpers.getOpcode(instruction);

            String instructionType = InstructionHelpers.getRegisterType(opcode.replace(" ", ""));
            
            String instructionDecoded = getBinary(instructionType, instruction, opcode);

            return instructionDecoded;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getBinary(String instructionType, String intruction, String opCode) {
        // TODO: Decoders
        String binary = intruction;

        // * Example:
        // switch (instructionType) {
        //     case "r":
        //         binary = decode.decodeRegistersTypeR(instruction) + decodeOP.decodeOpcode(opcode);
        //         break;
        //     case "j":
        //         binary = decode.decodeRegistersTypeJ(instruction) + decodeOP.decodeOpcode(opcode);
        //         break;
        //     case "i":
        //         binary = decode.decodeRegistersTypeI(instruction) + decodeOP.decodeOpcode(opcode);
        //         break;
        // }

        return binary;
    }
}