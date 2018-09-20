package utils;

import java.util.Objects;

public class Amount {
    private final int value;

    public Amount(int amount) {
        this.value = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return value == amount1.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "utils.Amount{" +
            "value=" + value +
            '}';
    }

    public Amount plus(Amount amount) {
        return new Amount(this.value + amount.getValue());
    }

    private int getValue() {
        return value;
    }

    public Amount minus(Amount amount) {
        return new Amount(this.value - amount.getValue());
    }
}
