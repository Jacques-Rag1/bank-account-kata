package bankaccount.service;

import bankaccount.service.Amount;
import bankaccount.service.Transaction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);

    @Test
    public void add_should_return_a_zero_amount(){
        Amount amountAfterAdding = new Transaction(new Amount(0)).add(new Amount(0));
        assertThat(amountAfterAdding).isEqualTo(new Amount(0));
    }
    @Test
    public void add_should_return_an_amount(){
        Amount amountAfterAdding = new Transaction(new Amount(0)).add(AMOUNT_100);
        assertThat(amountAfterAdding).isEqualTo(AMOUNT_100);
    }

    @Test
    public void add_should_return_the_balance_after_adding() {
        Transaction transaction = new Transaction(new Amount(0));
        Amount balance;
        transaction.add(AMOUNT_100);
        balance = transaction.add(AMOUNT_50);
        assertThat(balance).isEqualTo(new Amount(150));
    }
    @Test
    public void remove_should_return_a_zero_amount(){
        Amount amountAfterRemove = new Transaction(new Amount(0)).remove(new Amount(0));
        assertThat(amountAfterRemove).isEqualTo(new Amount(0));
    }
    @Test
    public void remove_should_return_an_amount(){
        Amount amountAfterRemove = new Transaction(new Amount(200)).remove(AMOUNT_100);
        assertThat(amountAfterRemove).isEqualTo(AMOUNT_100);
    }

    @Test
    public void remove_should_return_the_balance_after_removing() {
        Transaction transaction = new Transaction(new Amount(200));
        Amount balance;
        transaction.remove(AMOUNT_100);
        balance = transaction.remove(AMOUNT_50);
        assertThat(balance).isEqualTo(AMOUNT_50);
    }


}
