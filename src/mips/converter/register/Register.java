package mips.converter.register;

import java.util.Objects;

public class Register {
    private final String name; // $name
    private int baseValue; // start value 0 or value based before ($sp or $gp for example).
    private final int index; // index of vector

    public Register(String name, int baseValue, int index) {
        this.name = name;
        this.baseValue = baseValue;
        this.index = index;
    }

    public Register(String name, int index) {
        this.name = name;
        this.baseValue = 0;
        this.index = index;
    }

    public void setValue(int value) {
        this.baseValue = value;
    }

    public String getName() {
        return this.name;
    }


    public int getBaseValue() {
        return this.baseValue;
    }


    public int getIndex() {
        return this.index;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Register)) {
            return false;
        }
        Register register = (Register) o;
        return Objects.equals(name, register.name) && baseValue == register.baseValue && index == register.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseValue, index);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", baseValue='" + getBaseValue() + "'" +
            ", index='" + getIndex() + "'" +
            "}";
    }

}