import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

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

        account.makeDeposit(new Amount(100));

        assertThat(transactionsFake.balance).isEqualTo(100);
    }
    @Test
    public void make_deposite_can_agregate_amount(){

        account.makeDeposit(new Amount(100));
        account.makeDeposit(new Amount(100));

        assertThat(transactionsFake.balance).isEqualTo(200);
    }





    class TransactionsFake implements Transactions{
        public int balance = 0;

        public void add(Amount amount) {
            balance += amount.value;
        }
    }
}
