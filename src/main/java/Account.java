import java.util.ArrayList;

class Account {
    private Transactions transaction;
    private ArrayList<OperationStatement> history;
    private Amount balance;

    Account(Transactions transaction) {
        this.transaction = transaction;
        this.history = new ArrayList<>();
        balance = new Amount(0);
    }

    public void makeDeposit(Amount amount) {
        makeOperation(Operation.DEPOSIT, amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(Operation .WITHDRAWAL, amount);
    }

    private void makeOperation(Operation operation, Amount amount) {
        balance = transaction.add(operation, amount);
        history.add(new OperationStatement(operation, amount, balance));
    }

    public ArrayList getHistory() {
        return history;
    }
}
