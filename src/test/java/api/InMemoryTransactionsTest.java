package api;

import account.Amount;
import account.OperationType;
import account.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTransactionsTest {

    @Test
    public void getAll_should_return_all_added_transactions(){

        final InMemoryTransactions transactions = new InMemoryTransactions(() -> LocalDate.MIN);
        transactions.add(OperationType.DEPOSIT, new Amount(10), new Amount(10));

        final List<Transaction> actual = transactions.getAll();

        assertThat(actual).isEqualTo(Arrays.asList(new Transaction(OperationType.DEPOSIT, new Amount(10), new Amount(10), LocalDate.MIN)));
    }
}