package mips.converter.memory;

import java.io.IOException;
import java.util.ArrayList;

import mips.converter.io.WriteFile;

public class Memory {
    private static final String path = "out/memory.out"; // path of memory output
    private static final int MAX_SIZE = 4096; // valor maximo de memoria 4096 linhas = 16 kb
    private static ArrayList<MemoryBlock> memory; // bloco de memoria
    private static long index; // valor de mapping para index

    public static void init() {
        memory = null;
        long bits = 0;
        memory = new ArrayList<MemoryBlock>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++){
            index = Long.parseLong(Integer.toString(i));
            memory.add(i, new MemoryBlock(UtilsMemory.longToBytes(bits),UtilsMemory.longToBytes(0)));
            bits += 16;
        }
        exportData();
        printAll();
    }

    public static void start() throws IOException {

    }

    public static void setIndex(int slot){
        index = slot;
    }

    public static void setIndexAddress(String content){
        MemoryBlock memoryedited = memory.get(Integer.parseInt(Long.toString(index)));
        memoryedited.setValue(UtilsMemory.longToBytes(Long.parseLong(content)));
        memory.set(Integer.parseInt(Long.toString(index)), memoryedited);
    }

    public static Long getIndexAddress(String content) {
        for (MemoryBlock m : memory){
            if (Long.toString(m.getValueToLong()).equals(content)){
                index = memory.indexOf(m);
                return index;
            }
        }
        return null;
    }

    public static long getIndex(){
        return index;
    }

    public static MemoryBlock getIndexAddress() {
        return memory.get(Integer.parseInt(Long.toString(index)));
    }

    public static void printAll(){
        memory.forEach(m -> {
            UtilsMemory.printMemory(m);
        });
    }

    public static void exportData(){
        try {
            WriteFile.formatMemoryToFile(path, memory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}