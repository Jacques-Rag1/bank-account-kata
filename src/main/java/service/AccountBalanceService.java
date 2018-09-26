package service;

import core.AccountBalance;
import core.Amount;
import core.OperationType;

public class AccountBalanceService implements AccountBalance {
    private Amount balance;

    private AccountBalanceService(Amount balance) {
        this.balance = balance;
    }

    public static AccountBalanceService with(Amount amount){
        return new AccountBalanceService(amount);
    }
    @Override
    public boolean modify(OperationType operationType, Amount amount) {
        if (OperationType.DEPOSIT.equals(operationType)){
            balance = balance.plus(amount);
            return true;
        }
        if (OperationType.WITHDRAWAL.equals(operationType)){
            balance = balance.minus(amount);
            return true;
        }

        return false;
    }

    @Override
    public Amount getBalanceState() {
        return balance.makeClone();
    }
}
