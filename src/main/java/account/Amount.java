package account;

import java.util.Objects;

public class Amount {
    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    public Amount plus(Amount amount) {
        return new Amount(this.value + amount.value);
    }

    public Amount minus(Amount amount) {
        return new Amount(this.value - amount.value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
