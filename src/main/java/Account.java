import java.util.ArrayList;

class Account {
    private Transactions transaction;
    private ArrayList<OperationStatement> history;

    Account(Transactions transaction) {
        this.transaction = transaction;
        this.history = new ArrayList<>();
    }

    public void makeDeposit(Amount amount) {
        makeOperation(Operation.DEPOSIT, amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(Operation .WITHDRAWAL, amount);
    }

    private void makeOperation(Operation operation, Amount amount) {
        transaction.add(operation, amount);
        history.add(new OperationStatement(operation, amount));
    }

    public ArrayList getHistory() {
        return history;
    }
}
