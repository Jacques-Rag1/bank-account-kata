import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AccountTest {
    public Transactions transaction;

    @Test
    public void makeDeposit_should_make_a_deposit_of_zero(){
        transaction = mock(Transactions.class);
        Account account = new Account(transaction);
        account.makeDeposit(0);
        assertThat(false).isEqualTo(true);
    }
}
