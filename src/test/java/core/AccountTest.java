package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountTest {

    private Transactions transactions;
    private AccountLogPrinter accountLogPrinter;
    private AccountBalance accountBalance;
    private Account account;

    @BeforeEach
    public void initAccount() {
        transactions = mock(Transactions.class);
        accountLogPrinter = mock(AccountLogPrinter.class);
        accountBalance = mock(AccountBalance.class);
        account = new Account(transactions, accountLogPrinter, accountBalance);
        when(accountBalance.getBalanceAmount()).thenReturn(new Amount(10), new Amount(10), new Amount(10));
    }

    @Nested
    @DisplayName("makeDeposit should")
    class MakeDepositShould {
        @Test
        public void call_transactions_to_be_added_in_log() {

            account.makeDeposit(new Amount(100));

            verify(transactions).add(any(), eq(new Amount(100)), any(Amount.class));
        }
    }

    @Nested
    @DisplayName("makeWithdrawal should")
    class MakeWithdrawalShould {
        @Test
        public void call_transactions_to_be_added_in_log() {

            account.makeWithdrawal(new Amount(100));

            verify(transactions).add(any(), eq(new Amount(100)), any(Amount.class));
        }
    }

    @Test
    public void make_deposits_and_withdrawals() {
        account.makeDeposit(new Amount(200));
        account.makeWithdrawal(new Amount(100));
        account.makeDeposit(new Amount(50));

        verify(transactions).add(eq(OperationType.DEPOSIT), eq(new Amount(200)), any(Amount.class));
        verify(transactions).add(eq(OperationType.WITHDRAWAL), eq(new Amount(100)), any(Amount.class));
        verify(transactions).add(eq(OperationType.DEPOSIT), eq(new Amount(50)), any(Amount.class));
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
                new AccountStatement(OperationType.DEPOSIT, new Amount(200), new Amount(0)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100), new Amount(0)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50), new Amount(0))

                ));

            account.showHistory();

            List<AccountStatement> listExpected = Arrays.asList(
                new AccountStatement(OperationType.DEPOSIT, new Amount(200), new Amount(0)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100), new Amount(0)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50), new Amount(0))
            );
            verify(accountLogPrinter).print(listExpected);
        }
        @Test
        public void print_all_the_operations_we_did_with_balance(){
            account.makeDeposit(new Amount(200));
            account.makeWithdrawal(new Amount(100));
            account.makeDeposit(new Amount(50));
            when(transactions.getLog()).thenReturn(Arrays.asList(
                new AccountStatement(OperationType.DEPOSIT, new Amount(200), new Amount(200)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100), new Amount(100)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50), new Amount(150))

                ));

            account.showHistory();

            List<AccountStatement> listExpected = Arrays.asList(
                new AccountStatement(OperationType.DEPOSIT, new Amount(200), new Amount(200)),
                new AccountStatement(OperationType.WITHDRAWAL, new Amount(100), new Amount(100)),
                new AccountStatement(OperationType.DEPOSIT, new Amount(50), new Amount(150))
            );
            verify(accountLogPrinter).print(listExpected);
        }
    }
    @Test
    public void when_a_deposit_or_a_withdrawal_is_made_account_should_update_balance(){
        account.makeDeposit(new Amount(200));
        account.makeWithdrawal(new Amount(100));

        verify(accountBalance).modify(OperationType.DEPOSIT, new Amount(200));
        verify(accountBalance).modify(OperationType.WITHDRAWAL, new Amount(100));

    }

}
