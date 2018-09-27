package api;

import account.Transaction;
import account.TransactionsPrinter;

import java.util.List;

public class RecordingTransactionPrinter implements TransactionsPrinter {
    public StringBuilder stringBuilder = new StringBuilder();
    @Override
    public void print(List<Transaction> statements) {
        stringBuilder.append(statements.toString());
    }
}
