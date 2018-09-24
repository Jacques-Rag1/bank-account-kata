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
    private static final AmountPositive AMOUNT_100 = AmountPositive.of(100);
    private static final AmountPositive AMOUNT_200 = AmountPositive.of(200);
    private static final AmountPositive AMOUNT_0 = AmountPositive.of(0);
    private static final LocalDate DATE_OF_20_9_18 = LocalDate.of(2018, 9, 20);
    private static final LocalDate DATE_OF_1_1_2000 = LocalDate.of(2000, 1, 1);
    private Transactions transaction;
    private Dates date;
    private Balance balance;
    private Account account;

    @BeforeEach
    public void initAccount(){
        transaction = mock(Transactions.class);
        date = mock(Dates.class);
        balance = mock(Balance.class);
        account = new Account(transaction, date);
        account.joinBalance(balance);
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
    public void make_an_history_of_operation_with_balance(){

        when(balance.getCurrentBalance()).thenReturn(Amount.of(100));
        when(date.getCurrentDate()).thenReturn(DATE_OF_1_1_2000);

        ArrayList<OperationStatement> historyExpected = new ArrayList<>();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(100), () -> DATE_OF_1_1_2000));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_and_history_of_operation_with_date(){

        when(balance.getCurrentBalance()).thenReturn(Amount.of(100));
        when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);

        ArrayList<OperationStatement> historyExpected = new ArrayList<>();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(100),() ->DATE_OF_20_9_18));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_and_history_of_operation(){

        when(date.getCurrentDate()).thenReturn(DATE_OF_20_9_18);
        when(balance.getCurrentBalance()).thenReturn(Amount.of(100));

        ArrayList<OperationStatement> historyExpected = new ArrayList<>();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, Amount.of(100),() ->DATE_OF_20_9_18));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }


}
