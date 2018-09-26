package core;

import java.util.List;

public class Account {
    private final Transactions transactions;
    private final AccountLogPrinter accountLogPrinter;
    private final AccountBalance accountBalance;

    public Account(Transactions transactions, AccountLogPrinter accountLogPrinter, AccountBalance accountBalance) {

        this.transactions = transactions;
        this.accountLogPrinter = accountLogPrinter;
        this.accountBalance = accountBalance;
    }

    public void makeDeposit(Amount amount) {
        this.doOperation(amount, OperationType.DEPOSIT);
    }

    public void makeWithdrawal(Amount amount) {
        this.doOperation(amount, OperationType.WITHDRAWAL);
    }
    private void doOperation(Amount amount, OperationType operationType){
        accountBalance.modify(operationType, amount);
        transactions.add(operationType, amount, accountBalance.getBalanceAmount());
    }

    public void showHistory() {
        List<AccountStatement> accountStatements = transactions.getLog();
        accountLogPrinter.print(accountStatements);
    }
}
