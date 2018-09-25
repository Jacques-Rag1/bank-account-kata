package utils;

import java.time.LocalDate;
import java.util.List;

public interface Transactions {

    void add(Operation operation, Amount amount, Amount balance, LocalDate localDate);

    void printHistory();

    List<OperationStatement> getLog();
}
