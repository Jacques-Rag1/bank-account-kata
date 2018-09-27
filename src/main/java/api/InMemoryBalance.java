package api;

import account.Amount;
import account.OperationType;

public class InMemoryBalance implements account.Balance {
    private Amount balance;

    private InMemoryBalance(Amount balance) {
        this.balance = balance;
    }

    public static InMemoryBalance with(Amount amount){
        return new InMemoryBalance(amount);
    }
    @Override
    public void modify(OperationType operationType, Amount amount) {
        if (OperationType.DEPOSIT.equals(operationType)){
            balance = balance.plus(amount);
        }
        if (OperationType.WITHDRAWAL.equals(operationType)){
            balance = balance.minus(amount);
        }
    }

    @Override
    public Amount asAmount() {
        return balance;
    }
}
