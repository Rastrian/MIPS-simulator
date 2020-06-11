package mips.converter.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import mips.converter.register.Register;
import mips.converter.register.RegisterUtils;

/**
 * WriteFile
 */
public class WriteFile {

    public static void writeOutFile(String path, String line) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

        bufferedWriter.append(line + "\n");
        bufferedWriter.close();
    }

    public static void formatArrayListToFile(String path, ArrayList<String> array) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        array.forEach(a -> {
            try {
                bufferedWriter.append(a + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();
    }

    public static void formatRegistersToFile(String path, ArrayList<Register> array) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        array.forEach(r -> {
            try {
                bufferedWriter.append(RegisterUtils.printRegister(r) +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();
    }
}