package account;

public interface Balance {
    void modify(OperationType operationType, Amount amount);

    Amount asAmount();
}
