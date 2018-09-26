package core;

import java.time.LocalDate;
import java.util.Objects;

public class AccountStatement {
    private final OperationType operationType;
    private final Amount amount;
    private final Amount balance;
    private final LocalDate currentDate;

    public AccountStatement(OperationType operationType, Amount amount, Amount balance, LocalDate currentDate) {
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
        this.currentDate = currentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatement that = (AccountStatement) o;
        return operationType == that.operationType &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(currentDate, that.currentDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationType, amount, balance, currentDate);
    }

    @Override
    public String toString() {
        return "---------------------------------------------\n" +
            "Statement ->\n" +
            "\noperationType\t:\t" + operationType +
            "\namount\t\t\t:\t" + amount +
            "\nbalance\t\t\t:\t" + balance +
            "\ncurrentDate\t\t:\t" + currentDate +
            "\n---------------------------------------------";
    }
}
