package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AccountTest {
    private static final AmountPositive AMOUNT_100 = AmountPositive.of(100);
    private static final AmountPositive AMOUNT_200 = AmountPositive.of(200);
    private static final LocalDate DATE_OF_20_9_18 = LocalDate.of(2018, 9, 20);
    private static final LocalDate DATE_OF_1_1_2000 = LocalDate.of(2000, 1, 1);
    private Transactions transaction;
    private Dates date;
    private Balance balance;
    private Account account;

    @BeforeEach
    public void initAccount() {
        transaction = mock(Transactions.class);
        date = mock(Dates.class);
        balance = mock(Balance.class);
        account = new Account(transaction, date);
        account.joinBalance(balance);
    }

    @Nested
    @DisplayName("makeDeposit should")
    class MakeDepositShould {

        @Test
        public void make_a_deposit_of_Amount_object() {
            when(date.getCurrentDate()).thenReturn(DATE_OF_1_1_2000);
            when(balance.getCurrentBalance()).thenReturn(Amount.of(0));
            account.makeDeposit(AMOUNT_100);
            account.makeDeposit(AMOUNT_200);

            verify(transaction).add(Operation.DEPOSIT, AMOUNT_100, Amount.of(0), LocalDate.of(2000, 1, 1));
            verify(transaction).add(Operation.DEPOSIT, AMOUNT_200, Amount.of(0), LocalDate.of(2000, 1, 1));
        }
    }

    @Nested
    @DisplayName("makeWithdrawal should")
    class MakeWithdrawalShould {
        @Test
        public void make_a_withdrawal_of_amount() {
            when(date.getCurrentDate()).thenReturn(DATE_OF_1_1_2000);
            when(balance.getCurrentBalance()).thenReturn(Amount.of(0));
            account.makeWithdrawal(AMOUNT_100);
            account.makeWithdrawal(AMOUNT_200);

            verify(transaction).add(Operation.WITHDRAWAL, AMOUNT_100, Amount.of(0), LocalDate.of(2000, 1, 1));
            verify(transaction).add(Operation.WITHDRAWAL, AMOUNT_200, Amount.of(0), LocalDate.of(2000, 1, 1));
        }
    }

    @Test
    public void deposit_and_withdrawal() {
        when(date.getCurrentDate()).thenReturn(DATE_OF_1_1_2000);
        when(balance.getCurrentBalance()).thenReturn(Amount.of(0));
        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_200);
        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);

        verify(transaction).add(Operation.DEPOSIT, AMOUNT_100, Amount.of(0), LocalDate.of(2000, 1, 1));
        verify(transaction).add(Operation.DEPOSIT, AMOUNT_200, Amount.of(0), LocalDate.of(2000, 1, 1));
        verify(transaction).add(Operation.WITHDRAWAL, AMOUNT_100, Amount.of(0), LocalDate.of(2000, 1, 1));
        verify(transaction).add(Operation.WITHDRAWAL, AMOUNT_200, Amount.of(0), LocalDate.of(2000, 1, 1));
    }

    @Nested
    @DisplayName("getHistory should")
    class GetHistiryShould {
        @Test
        public void make_an_history_of_operation() {

            ArrayList historyExpected = new ArrayList();

            ArrayList history = account.getHistory();

            assertThat(history).isEqualTo(historyExpected);
        }

        @Test
        public void make_an_history_of_multiple_deposits_and_withdrawals() {

            when(date.getCurrentDate()).thenReturn(LocalDate.of(2000, 1, 1));
            when(balance.getCurrentBalance()).thenReturn(Amount.of(0));


            account.makeDeposit(AMOUNT_200);
            account.makeWithdrawal(AMOUNT_100);
            account.makeDeposit(AMOUNT_100);
            account.makeWithdrawal(AMOUNT_200);

            ArrayList history = account.getHistory();


            ArrayList<OperationStatement> historyExpected = new ArrayList<>();
            historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_200, Amount.of(0), () -> DATE_OF_1_1_2000));
            historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_100, Amount.of(0), () -> DATE_OF_1_1_2000));
            historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(0), () -> DATE_OF_1_1_2000));
            historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_200, Amount.of(0), () -> DATE_OF_1_1_2000));
            assertThat(history).isEqualTo(historyExpected);
        }

        @Test
        public void make_an_history_of_operation_with_balance() {

            when(balance.getCurrentBalance()).thenReturn(Amount.of(100));
            when(date.getCurrentDate()).thenReturn(DATE_OF_1_1_2000);

            ArrayList<OperationStatement> historyExpected = new ArrayList<>();
            historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(100), () -> DATE_OF_1_1_2000));

            account.makeDeposit(AMOUNT_100);
            ArrayList history = account.getHistory();

            assertThat(history).isEqualTo(historyExpected);
        }

        @Test
        public void make_an_history_of_operation_with_date() {

            when(balance.getCurrentBalance()).thenReturn(Amount.of(100));
            when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);

            ArrayList<OperationStatement> historyExpected = new ArrayList<>();
            historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(100), () -> DATE_OF_20_9_18));

            account.makeDeposit(AMOUNT_100);
            ArrayList history = account.getHistory();

            assertThat(history).isEqualTo(historyExpected);
        }
    }

    @Test
    public void make_and_history_of_operation() {

        when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);
        when(balance.getCurrentBalance()).thenReturn(Amount.of(100));

        account.makeDeposit(AMOUNT_100);
        List<OperationStatement> history = account.retrieveHistory();

        verify(transaction).getLog();
    }

    @Test
    public void showHistory_should_call_transactions_to_do_it() {

        when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);
        when(balance.getCurrentBalance()).thenReturn(Amount.of(100));

        account.makeDeposit(AMOUNT_100);
        account.showHistory();

        verify(transaction).printHistory();
    }


}
