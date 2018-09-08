import java.util.Objects;

public class OperationStatement {
    private OperationsType operationName;
    private Amount amount;
    private Amount balance;
    private CurrentDate currentDate;

    public OperationStatement(OperationsType operationName, Amount amount, Amount balance, CurrentDate currentDate) {
        this.operationName = operationName;
        this.amount = amount;
        this.balance = balance;
        this.currentDate = currentDate;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    public void setCurrentDate(CurrentDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
                "operationName=" + operationName +
                ", amount=" + amount +
                ", balance=" + balance +
                ", currentDate=" + currentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement statement = (OperationStatement) o;
        return operationName == statement.operationName &&
                Objects.equals(amount, statement.amount) &&
                Objects.equals(balance, statement.balance) &&
                Objects.equals(currentDate, statement.currentDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationName, amount, balance, currentDate);
    }
}
