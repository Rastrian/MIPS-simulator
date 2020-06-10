package mips.converter.memory;

import java.util.Objects;

public class Position {
    private String pos_addr;
    private String pos_content;

    public Position(String pos_addr, String pos_content) {
        this.pos_addr = pos_addr;
        this.pos_content = pos_content;
    }

    public String getAddress() {
        return this.pos_addr;
    }

    public void setAddress(String pos_addr) {
        this.pos_addr = pos_addr;
    }

    public String getPos_content() {
        return this.pos_content;
    }

    public void setPos_content(String pos_content) {
        this.pos_content = pos_content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(pos_addr, position.pos_addr) && Objects.equals(pos_content, position.pos_content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos_addr, pos_content);
    }

    @Override
    public String toString() {
        return "{" +
            " pos_addr='" + getAddress() + "'" +
            ", pos_content='" + getPos_content() + "'" +
            "}";
    }

}