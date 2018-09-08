import java.util.Objects;

public class OperationStatement {
    private OperationsType operationName;
    private Amount amount;

    public OperationStatement(OperationsType operationName, Amount amount) {
        this.operationName = operationName;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement statement = (OperationStatement) o;
        return operationName == statement.operationName &&
                Objects.equals(amount, statement.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationName, amount);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
                "operationName=" + operationName +
                ", amount=" + amount +
                '}';
    }
}
