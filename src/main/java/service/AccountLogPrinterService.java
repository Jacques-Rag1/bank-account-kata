package service;

import core.AccountLogPrinter;
import core.AccountStatement;

import java.util.List;

public class AccountLogPrinterService implements AccountLogPrinter {
    @Override
    public void print(List<AccountStatement> statements) {
        statements.forEach(System.out::println);
    }
}
