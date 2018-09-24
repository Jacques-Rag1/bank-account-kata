package utils;

import java.util.Objects;

public class Amount {
    private final int value;

    protected Amount(int amount) {
        this.value = amount;
    }

    public static Amount of(int amount) {
        return new Amount(amount);
    }
    

    public Amount plus(Amount amount) {
        return of(this.value + amount.value);
    }

    public Amount minus(Amount amount) {
        return of(this.value - amount.value);
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
