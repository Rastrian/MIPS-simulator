package mips.converter.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//import mips.converter.memory.MemoryBlock;

/**
 * ReadFile
 */
public class ReadFile {

    public static ArrayList<String> formatCommands(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        ArrayList<String> commandsList = new ArrayList<String>();

        while (bufferedReader.ready()) {
            commandsList.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        return commandsList;
    }

//    public static ArrayList<MemoryBlock> formatArrayListOfMemoryBlock(String path) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//
//        ArrayList<MemoryBlock> array = new ArrayList<MemoryBlock>();
//
//        while (bufferedReader.ready()) {
//            array.add();
//        }
//        bufferedReader.close();
//
//        return array;
//    }
}