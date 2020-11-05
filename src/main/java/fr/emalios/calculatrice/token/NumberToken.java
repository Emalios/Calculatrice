package fr.emalios.calculatrice.token;

import java.util.Objects;

public class NumberToken implements Token {

    private final int value;

    private NumberToken(int value) {
        this.value = value;
    }

    public static NumberToken of(int value) {
        return new NumberToken(value);
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberToken that = (NumberToken) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
