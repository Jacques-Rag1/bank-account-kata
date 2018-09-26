package core;

import java.util.List;

public class Account {
    private final Transactions transactions;
    private final AccountLogPrinter accountLogPrinter;

    public Account(Transactions transactions, AccountLogPrinter accountLogPrinter) {

        this.transactions = transactions;
        this.accountLogPrinter = accountLogPrinter;
    }

    public void makeDeposit(Amount amount) {
        this.doOperation(amount, OperationType.DEPOSIT);
    }

    public void makeWithdrawal(Amount amount) {
        this.doOperation(amount, OperationType.WITHDRAWAL);
    }
    private void doOperation(Amount amount, OperationType operationType){
        transactions.add(operationType, amount);
    }

    public void showHistory() {
        List<AccountStatement> accountStatements = transactions.getLog();
        accountLogPrinter.print(accountStatements);
    }
}
