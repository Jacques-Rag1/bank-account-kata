package bankaccount.service;

import bankaccount.util.CurrentDates;
import bankaccount.util.Operations;
import bankaccount.util.OperationsType;
import bankaccount.util.Transactions;

public class Account {


    private final Transactions transactions;
    private final Operations operations;
    private final CurrentDates currentDates;

    public Account(Transactions transactions, Operations operations, CurrentDates currentDates) {
        this.transactions = transactions;
        this.operations = operations;
        this.currentDates = currentDates;
    }

    public void makeDeposit(Amount amount) {
        makeOperation(OperationsType.DEPOSIT, amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(OperationsType.WITHDRAWAL, amount);
    }

    private void makeOperation(OperationsType operationsType, Amount amount){
        Amount balance;
        if (operationsType.equals(OperationsType.DEPOSIT)){
            balance = new Amount(transactions.add(amount).value);
        }
        else{
            balance = new Amount(transactions.remove(amount).value);
        }
        operations.addStatement(new OperationStatement(operationsType, amount, balance, currentDates.getCurrentDate()));
    }

    public void showOperationsHistory() {
        operations.showAllStatements();
    }
}
