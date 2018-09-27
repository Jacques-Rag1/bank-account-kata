package api;

import account.TransactionsPrinter;
import account.Transaction;

import java.util.List;

public class ConsoleTransactionsPrinter implements TransactionsPrinter {
    @Override
    public void print(List<Transaction> statements) {
        statements.forEach(System.out::println);
    }
}
