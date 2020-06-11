package mips.converter.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {
    public static File outFile(String filename) {
        Path currentRelativePath = Paths.get("out");
        String directory = currentRelativePath.toAbsolutePath().toString() + "";
        File dir = new File(directory);
        if (!dir.exists())
            dir.mkdirs();
        File createdFile = new File(dir.toString() + "/" + filename + ".out");
        try {
            FileWriter writeFile = new FileWriter(createdFile, true);
            writeFile.append("\n");
            writeFile.flush();
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createdFile;
    }
}