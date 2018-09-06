import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        TransactionsFake transactionsFake = new TransactionsFake();
        Account account = new Account(transactionsFake);

        account.makeDeposit(0);

        assertThat(transactionsFake.balance).isEqualTo(0);
    }


    class TransactionsFake implements Transactions{
        public int balance;

        public void add(int ammount) {
            balance = 0;
        }
    }
}
