package bankaccount.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    public static final Amount AMOUNT_100 = Amount.of(100);
    public static final Amount AMOUNT_50 = Amount.of(50);

    @Test
    public void add_should_return_a_zero_amount(){
        Amount amountAfterAdding = new Transaction(Amount.of(0)).addAmount(Amount.of(0));
        assertThat(amountAfterAdding).isEqualTo(Amount.of(0));
    }
    @Test
    public void add_should_return_an_amount(){
        Amount amountAfterAdding = new Transaction(Amount.of(0)).addAmount(AMOUNT_100);
        assertThat(amountAfterAdding).isEqualTo(AMOUNT_100);
    }

    @Test
    public void add_should_return_the_balance_after_adding() {
        Transaction transaction = new Transaction(Amount.of(0));
        Amount balance;
        transaction.addAmount(AMOUNT_100);
        balance = transaction.addAmount(AMOUNT_50);
        assertThat(balance).isEqualTo(Amount.of(150));
    }
    @Test
    public void remove_should_return_a_zero_amount(){
        Amount amountAfterRemove = new Transaction(Amount.of(0)).removeAmount(Amount.of(0));
        assertThat(amountAfterRemove).isEqualTo(Amount.of(0));
    }
    @Test
    public void remove_should_return_an_amount(){
        Amount amountAfterRemove = new Transaction(Amount.of(200)).removeAmount(AMOUNT_100);
        assertThat(amountAfterRemove).isEqualTo(AMOUNT_100);
    }

    @Test
    public void remove_should_return_the_balance_after_removing() {
        Transaction transaction = new Transaction(Amount.of(200));
        Amount balance;
        transaction.removeAmount(AMOUNT_100);
        balance = transaction.removeAmount(AMOUNT_50);
        assertThat(balance).isEqualTo(AMOUNT_50);
    }


}
