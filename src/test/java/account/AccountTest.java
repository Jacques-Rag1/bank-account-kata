package account;

import api.RecordingTransactionPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountTest {

    public static final LocalDate DATE_OF_26_9_18 = LocalDate.of(2018, 9, 26);
    private Transactions transactions;
    private TransactionsPrinter transactionsPrinter;
    private Balance balance;
    private Clock clock;
    private Account account;

    @BeforeEach
    public void initAccount() {
        transactions = mock(Transactions.class);
        transactionsPrinter = mock(TransactionsPrinter.class);
        balance = mock(Balance.class);
        clock = mock(Clock.class);
        account = new Account(transactions, transactionsPrinter, balance);
    }

    @Nested
    @DisplayName("makeDeposit should")
    class MakeDepositShould {
        @Test
        public void add_a_deposit_transaction() {

            account.makeDeposit(new Amount(100));

            verify(transactions).add(eq(OperationType.DEPOSIT), eq(new Amount(100)), any());
        }
    }

    @Nested
    @DisplayName("makeWithdrawal should")
    class MakeWithdrawalShould {
        @Test
        public void add_a_withdrawal_transaction() {

            account.makeWithdrawal(new Amount(100));

            verify(transactions).add(eq(OperationType.WITHDRAWAL), eq(new Amount(100)), any());
        }
    }

    @Test
    public void making_deposit_and_withdrawal_should_add_transactions() {
        account.makeDeposit(new Amount(200));
        account.makeWithdrawal(new Amount(100));
        account.makeDeposit(new Amount(50));

        verify(transactions).add(eq(OperationType.DEPOSIT), eq(new Amount(200)), any());
        verify(transactions).add(eq(OperationType.WITHDRAWAL), eq(new Amount(100)), any());
        verify(transactions).add(eq(OperationType.DEPOSIT), eq(new Amount(50)), any());
    }

    @Test
    public void when_a_deposit_or_a_withdrawal_is_made_account_should_update_balance() {
        account.makeDeposit(new Amount(200));
        account.makeWithdrawal(new Amount(100));

        verify(balance).modify(OperationType.DEPOSIT, new Amount(200));
        verify(balance).modify(OperationType.WITHDRAWAL, new Amount(100));

    }
    @Nested
    @DisplayName("showHistory should")
    class ShowHistoryShould {


        @Test
        public void print_all_the_transactions() {
            when(clock.now()).thenReturn(DATE_OF_26_9_18);
            account.makeDeposit(new Amount(200));
            when(clock.now()).thenReturn(DATE_OF_26_9_18.plusDays(1));
            account.makeWithdrawal(new Amount(100));
            List<Transaction> listExpected = Arrays.asList(
                new Transaction(OperationType.DEPOSIT, new Amount(200), new Amount(200), DATE_OF_26_9_18),
                new Transaction(OperationType.WITHDRAWAL, new Amount(50), new Amount(150), DATE_OF_26_9_18.plusDays(1))
            );

            when(transactions.getAll()).thenReturn(listExpected);

            account.showHistory();

            verify(transactionsPrinter).print(listExpected);
        }
        @Test
        public void the_transactions_should_be_print_to_be_readable() {
            RecordingTransactionPrinter transactionPrinter = new RecordingTransactionPrinter();
            Account account = new Account(transactions,transactionPrinter,balance);
            when(clock.now()).thenReturn(DATE_OF_26_9_18);
            account.makeDeposit(new Amount(200));
            when(clock.now()).thenReturn(DATE_OF_26_9_18.plusDays(1));
            account.makeWithdrawal(new Amount(100));
            List<Transaction> listExpected = Arrays.asList(
                new Transaction(OperationType.DEPOSIT, new Amount(200), new Amount(200), DATE_OF_26_9_18),
                new Transaction(OperationType.WITHDRAWAL, new Amount(50), new Amount(150), DATE_OF_26_9_18.plusDays(1))
            );
            when(transactions.getAll()).thenReturn(listExpected);

            account.showHistory();

            String expected =                                                           "[---------------------------------------------\n" +
                "Statement ->\n" +
                "\noperationType\t:\t" + "DEPOSIT" +
                "\namount\t\t\t:\t" + "200" +
                "\nbalance\t\t\t:\t" + "200" +
                "\ncurrentDate\t\t:\t" + "2018-09-26" +
                "\n---------------------------------------------, "+
                "---------------------------------------------\n" +
                "Statement ->\n" +
                "\noperationType\t:\t" + "WITHDRAWAL" +
                "\namount\t\t\t:\t" + "50" +
                "\nbalance\t\t\t:\t" + "150" +
                "\ncurrentDate\t\t:\t" + "2018-09-27" +
                "\n---------------------------------------------]";
            assertThat(transactionPrinter.stringBuilder.toString()).isEqualTo(expected);
        }

    }

}
