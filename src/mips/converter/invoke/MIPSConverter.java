package mips.converter.invoke;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import mips.converter.instructions.BinaryUtils;
import mips.converter.io.ReadFile;
import mips.converter.memory.Memory;
import mips.converter.register.RegisterMain;

/**
 * MIPSConverter
 */
public class MIPSConverter {
    public static void run(String inputPath, String outputPath, 
    String outputMemoryPath, String outputRegisterPath, String outputCache) throws Exception {
        File file = new File(outputPath);

        if (!file.createNewFile()) {
            FileWriter tempFileWriter = new FileWriter(file);
            tempFileWriter.write("");
            tempFileWriter.close();
        }

        FileWriter writeFile = new FileWriter(file, true);

        try {
            List<String> commands = ReadFile.formatCommands(inputPath);
            Memory.init();
            RegisterMain.init();

            for (int i = 0; i < commands.size(); i++) {
                String decoded = " ";
                
                if (commands.get(i).equals("nop")) {
                    writeFile.append(decoded + "\n");
                    writeFile.flush();
                } else {
                    decoded = BinaryUtils.processInstruction(commands.get(i));

                    writeFile.append(decoded + "\n");
                    writeFile.flush();
                }
            }

            writeFile.close();
			System.out.println("Conversão finalizada.");
        } catch (Exception e) {
            throw e;
        }
    }
}