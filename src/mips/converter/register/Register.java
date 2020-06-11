package mips.converter.register;

import java.util.Objects;

public class Register {
    private String name; // $name
    private long baseValue; // start value 0 or value based before ($sp or $gp for example).
    private int index; // index of vector
    private byte[] value;

    public Register(String name, int index) {
        this.name = name;
        this.value = RegisterUtils.longToBytes(0);
        this.baseValue = RegisterUtils.bytesToLong(value);
        this.index = index;
    }

    public void setValue(long value) {
        this.baseValue = value;
        this.value = RegisterUtils.longToBytes(value); 
    }

    public String getName() {
        return this.name;
    }

    public byte[] getValue() {
        return this.value;
    }

    public Long getBaseValue() {
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