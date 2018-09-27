package account;

import java.util.List;

public interface Transactions {
    void add(OperationType operationType, Amount amount, Amount balance);

    List<Transaction> getAll();
}
