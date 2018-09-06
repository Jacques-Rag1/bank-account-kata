import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);

    TransactionsFake transactionsFake;
    Account account;

    @Before
    public void varInit(){
        transactionsFake = new TransactionsFake();
        account = new Account(transactionsFake);
    }
    @Test
    public void make_deposit_should_delegate_to_transaction(){
        account.makeDeposit(new Amount(0));

        assertThat(transactionsFake.balance).isEqualTo(0);
    }
    @Test
    public void make_deposit_has_to_run_with_value(){

        account.makeDeposit(AMOUNT_100);

        assertThat(transactionsFake.balance).isEqualTo(100);
    }
    @Test
    public void make_deposite_can_agregate_amount(){

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_100);

        assertThat(transactionsFake.balance).isEqualTo(200);
    }

    @Test
    public void makeWithdrawal_should_send_request_to_transactions_to_remove() {
        transactionsFake.balance = 250;

        account.makeWithdrawal(AMOUNT_50);

        assertThat(transactionsFake.balance).isEqualTo(200);
    }

    @Test
    public void getOperationsHistory_should_return_a_list_of_all_operations() {
        transactionsFake.balance = 0;
        ArrayList<Operations> operations = new ArrayList<>() ;
        operations.add(Operations.DEPOSIT);
        operations.add(Operations.DEPOSIT);
        operations.add(Operations.WITHDRAWAL);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_100);

        assertThat(account.getOperationsHistory()).isEqualTo(operations);
    }


    class TransactionsFake implements Transactions{
        public int balance = 0;

        public List<Operations> operationsHistory = new ArrayList<>();

        public void add(Amount amount) {
            balance += amount.value;
            operationsHistory.add(Operations.DEPOSIT);
        }

        public void remove(Amount amount) {
            balance -= amount.value;
            operationsHistory.add(Operations.WITHDRAWAL);
        }

        @Override
        public ArrayList<Operations> getOperationsHistory() {
            return (ArrayList<Operations>) operationsHistory;
        }
    }
}
