package service;

import utils.Amount;
import utils.Operation;
import utils.Transactions;

public class TransactionService implements Transactions {
    private Amount currentBalance;

    public TransactionService(Amount currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public Amount add(Operation operation, Amount amount) {
        if (operation.equals(Operation.DEPOSIT)){
            currentBalance = currentBalance.plus(amount);
        }
        if (operation.equals(Operation.WITHDRAWAL)){
            currentBalance = currentBalance.minus(amount);
        }
        System.out.println("--> making transaction of " + operation + " with amount " + amount);
        return currentBalance;
    }
}
