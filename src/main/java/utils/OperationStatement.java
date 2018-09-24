package utils;

import java.time.LocalDate;
import java.util.Objects;

public class OperationStatement {
    private final Operation operation;
    private final Amount amount;
    private final Amount balance;
    private final LocalDate operationDate;

    public OperationStatement(Operation operation, Amount amount, Amount balance, Dates date) {

        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
        this.operationDate = date.getCurrentDate();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(operationDate, that.operationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operation, amount, balance, operationDate);
    }

    @Override
    public String toString() {
        return
            "-------------------------------------------------\n" +
            "Statement ->\n" +
            "operation \t: " + operation +
            "\namount \t\t: " + amount +
            "\nbalance \t: " + balance +
            "\ndate \t\t: " + operationDate +
            "\n-------------------------------------------------";
    }
}
