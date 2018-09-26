package core;

import java.util.List;

public interface Transactions {
    void add(OperationType operationType, Amount amount, Amount balance);

    List<AccountStatement> getLog();
}
