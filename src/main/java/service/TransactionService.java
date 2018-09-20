package service;

import utils.Amount;
import utils.Operation;
import utils.Transactions;

class TransactionService implements Transactions {
    private Amount currentBalance;

    public TransactionService(Amount currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public Amount add(Operation operation, Amount amount) {
        if (operation.equals(Operation.DEPOSIT)){
            currentBalance.plus(amount);
        }
        if (operation.equals(Operation.WITHDRAWAL)){
            currentBalance.minus(amount);
        }
        return currentBalance;
    }
}
