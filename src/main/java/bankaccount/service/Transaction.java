package bankaccount.service;

import bankaccount.util.Transactions;

public class Transaction implements Transactions {
    private final Amount balance;

    public Transaction(Amount balance) {
        this.balance = balance;
    }

    @Override
    public Amount add(Amount amount) {
        balance.plus(amount);
        return balance;
    }

    @Override
    public Amount remove(Amount amount) {
        balance.minus(amount);
        return balance;
    }
}
