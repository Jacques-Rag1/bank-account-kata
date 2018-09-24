package utils;

import java.time.LocalDate;

public interface Transactions {

    void add(Operation operation, Amount amount, Amount balance, LocalDate localDate);

    void printHistory();
}
