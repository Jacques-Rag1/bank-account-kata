import java.util.ArrayList;

class Account {
    private Transactions transaction;
    private Dates date;
    private ArrayList<OperationStatement> history;
    private Amount balance;

    public Account(Transactions transaction, Dates date) {
        this.transaction = transaction;
        this.date = date;
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
        history.add(new OperationStatement(operation, amount, balance, date.getCurrentDate()));
    }

    public ArrayList getHistory() {
        return history;
    }
}
