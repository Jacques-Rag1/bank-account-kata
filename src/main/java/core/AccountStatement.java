package core;

import java.util.Objects;

public class AccountStatement {
    private final OperationType operationType;
    private final Amount amount;
    private final Amount balance;

    public AccountStatement(OperationType operationType, Amount amount, Amount balance) {
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatement that = (AccountStatement) o;
        return operationType == that.operationType &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationType, amount, balance);
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
            "operationType=" + operationType +
            ", amount=" + amount +
            ", balance=" + balance +
            '}';
    }
}
