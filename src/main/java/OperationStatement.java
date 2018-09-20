import java.util.Objects;

class OperationStatement {
    private final Operation operation;
    private final Amount amount;
    private final Amount balance;

    public OperationStatement(Operation operation, Amount amount, Amount balance) {

        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operation, amount, balance);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
            "operation=" + operation +
            ", amount=" + amount +
            '}';
    }
}
