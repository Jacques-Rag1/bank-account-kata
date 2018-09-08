package bankaccount.service;

import java.util.Objects;

public class Amount {
    int value;

    Amount(int value) {
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

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                '}';
    }

    public void plus(Amount amount) {
        this.value += amount.value;
    }

    public void minus(Amount amount) {
        this.value -= amount.value;
    }
}
