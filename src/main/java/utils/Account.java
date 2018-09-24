package utils;

import java.util.ArrayList;

public class Account {
    private Transactions transactions;
    private Dates date;
    private ArrayList<OperationStatement> history;
    private Balance balance;

    public Account(Transactions transactions, Dates date) {
        this.transactions = transactions;
        this.date = date;
        this.history = new ArrayList<>();
    }
    public void joinBalance(Balance balance){
        this.balance = balance;
    }

    public void makeDeposit(AmountPositive amount) {
        balance.addMoney(amount);
        makeOperation(Operation.DEPOSIT, amount);

    }

    public void makeWithdrawal(AmountPositive amount) {
        balance.removeMoney(amount);
        makeOperation(Operation.WITHDRAWAL, amount);
    }

    private void makeOperation(Operation operation, Amount amount) {

        transactions.add(operation, amount, balance.getCurrentBalance(), date.getCurrentDate());
        history.add(
            new OperationStatement(operation, amount,
                    balance.getCurrentBalance(), date) );
    }

    public ArrayList getHistory() {
        return history;
    }

    public void showHistory() {
        transactions.printHistory();
    }
}
