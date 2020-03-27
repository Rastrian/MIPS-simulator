package mips.converter.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * WriteFile
 */
public class WriteFile {

    public void writeBinary(String path, String line) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

        bufferedWriter.append(line + "\n");
        bufferedWriter.close();
    }
}