package service;

import utils.Amount;
import utils.Operation;
import utils.OperationStatement;
import utils.Transactions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements Transactions {

    private List<OperationStatement> log;

    public TransactionService() {
        log = new ArrayList<>();
    }

    @Override
    public void add(Operation operation, Amount amount, Amount balance, LocalDate localDate) {

        log.add(new OperationStatement(operation,amount,balance, () -> localDate));
        System.out.println("--> making transaction of " + operation + " with amount " + amount);
    }

    @Override
    public void printHistory() {
        log.forEach(System.out::println);
    }
}
