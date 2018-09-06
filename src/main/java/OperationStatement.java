import java.util.Objects;

class OperationStatement {
    private Operations operation;
    private Amount amount;
    private OperationDate operationDate;

    public OperationStatement(Operations operation, Amount amount, OperationDate operationDate) {
        this.operation = operation;
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public Operations getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
            "operation=" + operation +
            ", amount=" + amount +
            ", operationDate=" + operationDate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(operationDate, that.operationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operation, amount, operationDate);
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public OperationDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(OperationDate operationDate) {
        this.operationDate = operationDate;
    }
}
