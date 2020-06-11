package mips.converter.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadFile
 */
public class ReadFile {

    public static ArrayList<String> formatArrayList(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        ArrayList<String> commandsList = new ArrayList<String>();

        while (bufferedReader.ready()) {
            commandsList.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        return commandsList;
    }
}