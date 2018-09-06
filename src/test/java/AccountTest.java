import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        TransactionsFake transactionsFake = new TransactionsFake();
        Account account = new Account(transactionsFake);

        account.makeDeposit(new Amount(0));

        assertThat(transactionsFake.balance).isEqualTo(0);
    }
    @Test
    public void make_deposit_has_to_run_with_value(){
        TransactionsFake transactionsFake = new TransactionsFake();
        Account account = new Account(transactionsFake);

        account.makeDeposit(new Amount(100));

        assertThat(transactionsFake.balance).isEqualTo(100);
    }





    class TransactionsFake implements Transactions{
        public int balance;

        public void add(Amount amount) {
            balance = amount.value;
        }
    }
}
