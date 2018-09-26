package service;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionsService implements Transactions {
    private List<AccountStatement> log;
    private OperationDate operationDate;

    public TransactionsService(OperationDate operationDate) {
        this.operationDate = operationDate;
        this.log = new ArrayList<>();
    }

    @Override
    public void add(OperationType operationType, Amount amount, Amount balance) {
        log.add(new AccountStatement(operationType,amount,balance, operationDate.getCurrentDate()));
    }

    @Override
    public List<AccountStatement> getLog() {
        return log;
    }
}
