package core;

import java.util.Objects;

public class AccountStatement {
    private final OperationType operationType;
    private final Amount amount;

    public AccountStatement(OperationType operationType, Amount amount) {
        this.operationType = operationType;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatement that = (AccountStatement) o;
        return operationType == that.operationType &&
            Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationType, amount);
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
            "operationType=" + operationType +
            ", amount=" + amount +
            '}';
    }
}
