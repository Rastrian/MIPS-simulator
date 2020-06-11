package mips.converter.memory;

import java.io.IOException;
import java.util.ArrayList;

import mips.converter.io.ReadFile;
import mips.converter.io.WriteFile;

public class Memory {
    private static final String path = "out/memory.out"; // path of memory output
    private static final int MAX_SIZE = 4096; // valor maximo de memoria 4096 linhas = 16 kb
    private static ArrayList<String> memory; // bloco de memoria
    private static long index; // valor de mapping para index

    public static int index(int pos) {
        return (pos / 16);
    }

    public static void init() {
        memory = null;
        int bits = 0;
        String hex = "";
        memory = new ArrayList<String>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++){
            hex = Integer.toHexString(bits);
            if (hex != null)
                index = Long.parseLong(Integer.toString(i));
                memory.set(index(i), "0x0" + hex + " 0x00000000");
                if (i <= 15)
                    memory.set(index(i), "0x" + hex + " 0x00000000");
                bits += 16;
        }
        exportData();
    }

    public static void start() throws IOException {
        memory = ReadFile.formatArrayList(path); 
        for (int i = 0; i < MAX_SIZE; i++){
            System.out.println("");
        }
    }

    public static void setIndex(int slot){
        index = slot;
    }

    public static void setIndexAddress(String content){
        memory.set(Integer.parseInt(Long.toString(index)), content);
    }

    public static long getIndex(){
        return index;
    }

    public static String getIndexAddress(){
        return memory.get(Integer.parseInt(Long.toString(index)));
    }

    public static void printAll(){
        index = 0;
        memory.forEach(m -> {
            System.out.println(m.toString());
            index++;
        });
    }

    public static void exportData(){
        try {
            WriteFile.formatArrayListToFile(path, memory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}