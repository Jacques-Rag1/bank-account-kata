package bankaccount.service;

import bankaccount.util.CurrentDates;
import bankaccount.util.OperationsType;

import java.util.Objects;

public class OperationStatement {
    private final OperationsType operationName;
    private final Amount amount;
    private final Amount balance;
    private final CurrentDates currentDates;

    public OperationStatement(OperationsType operationName, Amount amount, Amount balance, CurrentDates currentDates) {
        this.operationName = operationName;
        this.amount = amount;
        this.balance = balance;
        this.currentDates = currentDates;
    }

    @Override
    public String toString() {
        return "\n-----------------------------------------------------------\n" +
                "Operation Name\t:\t" + operationName +
                "\nAmount\t\t\t:\t" + amount +
                "\nBalance\t\t\t:\t" + balance +
                "\nDate\t\t\t:\t" + currentDates +
                "\n-----------------------------------------------------------\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement statement = (OperationStatement) o;
        return operationName == statement.operationName &&
                Objects.equals(amount, statement.amount) &&
                Objects.equals(balance, statement.balance) &&
                Objects.equals(currentDates, statement.currentDates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationName, amount, balance, currentDates);
    }
}
