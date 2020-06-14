package mips.converter.register;

import java.io.IOException;
import java.util.ArrayList;

import mips.converter.io.WriteFile;

public class RegisterMain {
    private static final String path = "out/registers.out"; // path of registers output
    public static final int MAX_REGISTERS = 32; // maximum of registrators
    private static ArrayList<Register> registers; // list of registrators

    // init all 32 registers
    public static void init() {
        int number;
        registers = null;
        registers = new ArrayList<Register>(MAX_REGISTERS);
        registers.add(new Register("zero", 0));
        registers.add(new Register("at", 1));
        number = 2;
        for (int i = 0; i < 2; i++, number++) {
            registers.add(new Register("v" + i, number));
        }
        number = 4;
        for (int i = 0; i < 4; i++, number++) {
            registers.add(new Register("a" + i, number));
        }
        number = 8;
        for (int i = 0; i < 8; i++, number++) {
            registers.add(new Register("t" + i, number));
        }
        number = 16;
        for (int i = 0; i < 8; i++, number++) {
            registers.add(new Register("s" + i, number));
        }
        registers.add(new Register("t8", 24));
        registers.add(new Register("t9", 25));
        registers.add(new Register("k0", 26));
        registers.add(new Register("k1", 27));
        registers.add(new Register("gp", 28));
        registers.add(new Register("sp", 29));
        registers.add(new Register("fp", 30));
        registers.add(new Register("ra", 31));
        registers.add(new Register("hi", 32));
        registers.add(new Register("lo", 33));
        if (registers.size() < MAX_REGISTERS) {
            System.out.println("Erro: Quantidade de Registradores insuficiente, size = " + registers.size());
            return;
        }
        if (registers.size() >= MAX_REGISTERS) {
            registers.forEach(r -> {
                System.out.println(RegisterUtils.printRegister(r));
            });
        }
        exportData();
    }

    public static ArrayList<Register> getRegisters() {
        return registers;
    }

    public static int size() {
        return registers.size();
    }

    public static Register getRegister(String name) {
        for (Register r : registers){
            if (r.getName() == name || r.getName() == "$"+name){
                return r;
            }
        }
        return null;
    }

    public static Register getRegisterByIndex(int index) {
        for (Register r : registers){
            if (r.getIndex() == index){
                return r;
            }
        }
        return null;
    }

    public static int getRegisterIndex(String name) {
        for (Register r : registers){
            if (r.getName() == name || r.getName() == "$"+name){
                return r.getIndex();
            }
        }
        return -1;
    }

    public static String getRegisterBinary(String name) {
        for (Register r : registers){
            if (r.getName().equals(name) || r.getName().equals("$"+name)){
                String result = RegisterUtils.REGISTERS_OPCODE.get(name);
                if (result == null)
                    RegisterUtils.REGISTERS_OPCODE.get(name.replaceAll("$", ""));
                return result;
            }
        }
        return null;
    }

    public static boolean updateRegister(String name, long value) {
        for (Register r : registers){
            if (r.getName() == name || r.getName() == "$"+name){
                r.setValue(value);
                if (r.getBaseValue() == value)
                    return true;
            }
        }
        return false;
    }

    public static void exportData(){
        try {
            WriteFile.formatRegistersToFile(path, registers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}