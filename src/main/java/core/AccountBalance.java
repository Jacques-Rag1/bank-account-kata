package core;

public interface AccountBalance {
    public boolean modify(OperationType operationType, Amount amount);

    Amount getBalanceAmount();
}
