package utils;

import java.util.Objects;

public class Amount {
    private final int value;

    private Amount(int amount) {
        this.value = amount;
    }

    public static Amount createAmount(int amount) {
        if (amount < 0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        return new Amount(amount);
    }


    public Amount plus(Amount amount) {
        return createAmount(this.value + amount.getValue());
    }

    public Amount minus(Amount amount) {
        return createAmount(this.value - amount.getValue());
    }

    private int getValue() {
        return value;
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
        return "" + value;
    }

}
