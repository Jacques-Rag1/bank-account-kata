import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountTest {
    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_200 = new Amount(200);
    public static final Amount AMOUNT_0 = new Amount(0);
    public Transactions transaction;

    @Test
    public void makeDeposit_should_make_a_deposit_of_zero(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

        account.makeDeposit(AMOUNT_0);

        verify(transaction).add(Operation.DEPOSIT,AMOUNT_0);
    }

    @Test
    public void makeDeposit_should_make_a_deposit_of_Amount_object() {
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_200);

        verify(transaction).add(Operation.DEPOSIT,AMOUNT_100);
        verify(transaction).add(Operation.DEPOSIT,AMOUNT_200);
    }

    @Test
    public void makeWithdrawal_should_make_a_withdrawal_of_amount(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);

        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_100);
        verify(transaction).add(Operation.WITHDRAWAL,AMOUNT_200);
    }
    @Test
    public void deposit_an_withdrawal_have_to_be_send_using_keywords(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

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
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

        ArrayList historyExpected = new ArrayList();

        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_an_history_of_multiple_deposits_and_withdrawals(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);
        when(transaction.add(Operation.DEPOSIT, AMOUNT_200)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.DEPOSIT, AMOUNT_100)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.WITHDRAWAL, AMOUNT_200)).thenReturn(AMOUNT_0);
        when(transaction.add(Operation.WITHDRAWAL, AMOUNT_100)).thenReturn(AMOUNT_0);

        ArrayList historyExpected = new ArrayList();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_200, new Amount(0)));
        historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_100, new Amount(0)));
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, new Amount(0)));
        historyExpected.add(new OperationStatement(Operation.WITHDRAWAL, AMOUNT_200, new Amount(0)));

        account.makeDeposit(AMOUNT_200);
        account.makeWithdrawal(AMOUNT_100);
        account.makeDeposit(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_200);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }
    @Test
    public void make_an_history_of_operation_with_balance(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);
        when(transaction.add(Operation.DEPOSIT, AMOUNT_100)).thenReturn(AMOUNT_100);

        ArrayList historyExpected = new ArrayList();
        historyExpected.add(new OperationStatement(Operation.DEPOSIT, AMOUNT_100, new Amount(100)));

        account.makeDeposit(AMOUNT_100);
        ArrayList history = account.getHistory();

        assertThat(history).isEqualTo(historyExpected);
    }

}
