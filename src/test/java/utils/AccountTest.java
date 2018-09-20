package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountTest {
    public static final Amount AMOUNT_100 = Amount.createAmount(100);
    public static final Amount AMOUNT_200 = Amount.createAmount(200);
    public static final Amount AMOUNT_0 = Amount.createAmount(0);
    public static final LocalDate DATE_OF_20_9_18 = LocalDate.of(2018, 9, 20);
    public Transactions transaction;
    public Dates date;
    Account account;

    @BeforeEach
    public void initAccount(){
        transaction = mock(Transactions.class);
        date = mock(Dates.class);
        account = new Account(transaction, date);
    }
    @Test
    public void makeDeposit_should_make_a_deposit_of_zero(){

        account.makeDeposit(AMOUNT_0);

        verify(transaction).add(Operation.DEPOSIT,AMOUNT_0);
    }

    @Test
    public void makeDeposit_should_make_a_deposit_of_Amount_object() {

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_200);

        verify(transaction).add(Operation.DEPOSIT,AMOUNT_100);
        verify(transaction).add(Operation.DEPOSIT,AMOUNT_200);
    }

    @Test
    public void makeWithdrawal_should_make_a_withdrawal_of_amount(){

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);

        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_100);
        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_200);
    }
    @Test
    public void deposit_an_withdrawal_have_to_be_send_using_keywords(){

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_200);
        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);

        verify(transaction).add(Operation.DEPOSIT,AMOUNT_100);
        verify(transaction).add(Operation.DEPOSIT,AMOUNT_200);
        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_100);
        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_200);
    }
    @Test
    public void make_an_history_of_operation(){

        ArrayList historyExpected = new ArrayList();

        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_an_history_of_multiple_deposits_and_withdrawals(){

        when(date.getCurrentDate()).thenReturn(LocalDate.of(2000,1,1));
        when(transaction.add(Operation.DEPOSIT, AMOUNT_200)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.DEPOSIT, AMOUNT_100)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.WITHDRAWAL, AMOUNT_200)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.WITHDRAWAL, AMOUNT_100)).thenReturn(AMOUNT_0);

        ArrayList historyExpected = new ArrayList();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_200, Amount.createAmount(0)));
        historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_100, Amount.createAmount(0)));
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.createAmount(0)));
        historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_200, Amount.createAmount(0)));

        account.makeDeposit(AMOUNT_200);
        account.makeWithdrawal(AMOUNT_100);
        account.makeDeposit(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_an_history_of_operation_with_balance(){
        when(transaction.add(Operation.DEPOSIT, AMOUNT_100)).thenReturn(AMOUNT_100);
        when(date.getCurrentDate()).thenReturn(LocalDate.of(2000,1,1));

        ArrayList historyExpected = new ArrayList();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.createAmount(100)));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_an_history_of_operation_with_date(){
       when(transaction.add(Operation.DEPOSIT, AMOUNT_100)).thenReturn(AMOUNT_100);
        when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);

        ArrayList historyExpected = new ArrayList();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.createAmount(100),DATE_OF_20_9_18));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }


}
