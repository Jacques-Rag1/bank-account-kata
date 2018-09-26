package core;

public class Account {
    private final Transactions transactions;

    public Account(Transactions transactions) {

        this.transactions = transactions;
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
}
