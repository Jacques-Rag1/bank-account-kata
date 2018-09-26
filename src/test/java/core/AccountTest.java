package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountTest {

    Transactions transactions;
    AccountLogPrinter accountLogPrinter;
    Account account;

    @BeforeEach
    public void initAccount() {
        transactions = mock(Transactions.class);
        accountLogPrinter = mock(AccountLogPrinter.class);
        account = new Account(transactions, accountLogPrinter);
    }

    @Nested
    @DisplayName("makeDeposit should")
    class MakeDepositShould {
        @Test
        public void call_transactions_to_be_added_in_log() {

            account.makeDeposit(new Amount(100));

            verify(transactions).add((OperationType) any(), eq(new Amount(100)));
        }
    }

    @Nested
    @DisplayName("makeWithdrawal should")
    class MakeWithdrawalShould {
        @Test
        public void call_transactions_to_be_added_in_log() {

            account.makeWithdrawal(new Amount(100));

            verify(transactions).add((OperationType) any(), eq(new Amount(100)));
        }
    }

    @Test
    public void make_deposits_and_withdrawals() {
        account.makeDeposit(new Amount(200));
        account.makeWithdrawal(new Amount(100));
        account.makeDeposit(new Amount(50));

        verify(transactions).add(OperationType.DEPOSIT, new Amount(200));
        verify(transactions).add(OperationType.WITHDRAWAL, new Amount(100));
        verify(transactions).add(OperationType.DEPOSIT, new Amount(50));
    }

    @Nested
    @DisplayName("showHistory should")
    class ShowHistoryShould {
        @Test
        public void print_all_the_operations_we_did(){
            account.makeDeposit(new Amount(200));
            account.makeWithdrawal(new Amount(100));
            account.makeDeposit(new Amount(50));
            when(transactions.getLog()).thenReturn(Arrays.asList(
                new AccountStatement(OperationType.DEPOSIT, new Amount(200)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50))

                ));

            account.showHistory();

            List<AccountStatement> listExpected = Arrays.asList(
                new AccountStatement(OperationType.DEPOSIT, new Amount(200)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50))
            );
            verify(accountLogPrinter).print(listExpected);
        }

    }

}
