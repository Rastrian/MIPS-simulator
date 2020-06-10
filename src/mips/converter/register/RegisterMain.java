package mips.converter.register;

import java.util.ArrayList;

public class RegisterMain {
    public static final int MAX_REGISTERS = 32;
    private static ArrayList<Register> registers;

    public RegisterMain() {
    }

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
        if (registers.size() != MAX_REGISTERS) {
            System.out.println("Erro: Quantidade de Registradores insuficiente, size = " + registers.size());
            return;
        }
        if (registers.size() >= MAX_REGISTERS) {
            registers.forEach(r -> {
                System.out.println(r.getIndex() + "° - " + r.getName() + " - " + r.getBaseValue());
            });
        }
    }

    public ArrayList<Register> getRegisters() {
        return registers;
    }

    public Register getRegister(String name) {
        for (Register r : registers){
            if (r.getName() == name || r.getName() == "$"+name){
                return r;
            }
        }
        return null;
    }

    public boolean updateRegister(String name, int value) {
        for (Register r : registers){
            if (r.getName() == name || r.getName() == "$"+name){
                r.setValue(value);
                if (r.getBaseValue() == value)
                    return true;
            }
        }
        return false;
    }

}