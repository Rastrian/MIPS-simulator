package mips.converter.memory;

import java.util.Objects;

public class MemoryBlock {
    private byte[] name;
    private byte[] value;

    public MemoryBlock(byte[] name, byte[] value) {
        this.name = name;
        this.value = value;
    }

    public void setName(byte[] name) {
        this.name = name;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return this.value;
    }

    public byte[] getName() {
        return this.name;
    }

    public long getValueToLong() {
        return UtilsMemory.bytesToLong(this.value);
    }

    public long getNameToLong() {
        return UtilsMemory.bytesToLong(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MemoryBlock)) {
            return false;
        }
        MemoryBlock memoryBlock = (MemoryBlock) o;
        return Objects.equals(name, memoryBlock.name) && Objects.equals(value, memoryBlock.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}