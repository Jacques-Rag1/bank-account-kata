import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        verify(transaction).add(AMOUNT_0);
    }

    @Test
    public void makeDeposit_should_make_a_deposit_of_Amount_object() {
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_200);

        verify(transaction).add(AMOUNT_100);
        verify(transaction).add(AMOUNT_200);
    }
}
