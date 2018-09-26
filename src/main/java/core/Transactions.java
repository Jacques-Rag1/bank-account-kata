package core;

import java.util.List;

public interface Transactions {
    void add(OperationType operationType, Amount amount);

    List<AccountStatement> getLog();
}
