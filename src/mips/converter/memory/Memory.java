package mips.converter.memory;

import java.util.ArrayList;

public class Memory {
    private ArrayList<String> slots;
    private static final int MAX_SIZE = 4096;
    private static Position[] position;
    private static int count = 0;

    public Memory() {}

    public void init(){
        position = new Position[MAX_SIZE];
        slots = new ArrayList<String>(MAX_SIZE);
    }

    public void start(){}

    public static int index(int pos){
        return (pos/16);
    }

    public static void setSlot(int slot, String content){
        position[index(slot)].setPos_content(content);
    }

    public static Position getSlot(int slot) {
        return position[index(slot)];
    }
}