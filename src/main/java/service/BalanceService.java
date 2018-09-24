package service;

import utils.Amount;
import utils.AmountPositive;
import utils.Balance;

public class BalanceService implements Balance {
    private Amount currentBalance;

    public BalanceService() {
        this.currentBalance = Amount.of(0);
    }
    @Override
    public Amount getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public boolean addMoney(AmountPositive amount) {
        currentBalance = currentBalance.plus(amount);
        return true;
    }

    @Override
    public boolean removeMoney(AmountPositive amount) {
        currentBalance = currentBalance.minus(amount);
        return true;
    }
}
