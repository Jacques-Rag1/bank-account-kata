package api;

import account.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactions implements Transactions {
    private List<Transaction> log;
    private Clock clock;

    public InMemoryTransactions(Clock clock) {
        this.clock = clock;
        this.log = new ArrayList<>();
    }

    @Override
    public void add(OperationType operationType, Amount amount, Amount balance) {
        log.add(new Transaction(operationType,amount,balance, clock.now()));
    }

    @Override
    public List<Transaction> getAll() {
        return log;
    }
}
