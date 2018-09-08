import java.util.Objects;

public class OperationStatement {
    private OperationsType operationName;
    private Amount amount;
    private Amount balance;

    public OperationStatement(OperationsType operationName, Amount amount, Amount balance) {
        this.operationName = operationName;
        this.amount = amount;
        this.balance = balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement statement = (OperationStatement) o;
        return operationName == statement.operationName &&
                Objects.equals(amount, statement.amount) &&
                Objects.equals(balance, statement.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationName, amount, balance);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
                "operationName=" + operationName +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
