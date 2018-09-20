import java.util.Objects;

class OperationStatement {
    private final Operation operation;
    private final Amount amount;

    public OperationStatement(Operation operation, Amount amount) {
        this.operation = operation;

        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
            Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operation, amount);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
            "operation=" + operation +
            ", amount=" + amount +
            '}';
    }
}
