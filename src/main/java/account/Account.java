package account;

import java.util.List;

public class Account {
    private final Transactions transactions;
    private final TransactionsPrinter transactionsPrinter;
    private final Balance balance;

    public Account(Transactions transactions, TransactionsPrinter transactionsPrinter, Balance balance) {

        this.transactions = transactions;
        this.transactionsPrinter = transactionsPrinter;
        this.balance = balance;
    }

    public void makeDeposit(Amount amount) {
        this.doOperation(amount, OperationType.DEPOSIT);
    }

    public void makeWithdrawal(Amount amount) {
        this.doOperation(amount, OperationType.WITHDRAWAL);
    }
    private void doOperation(Amount amount, OperationType operationType){
        balance.modify(operationType, amount);
        transactions.add(operationType, amount, balance.asAmount());
    }

    public void showHistory() {
        List<Transaction> transactions = this.transactions.getAll();
        transactionsPrinter.print(transactions);
    }
}
