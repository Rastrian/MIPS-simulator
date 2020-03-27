package mips.converter.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadFile
 */
public class ReadFile {

    public static List<String> getCommands(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        List<String> commandsList = new ArrayList<String>();

        while (bufferedReader.ready()) {
            commandsList.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        return commandsList;
    }
}