package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Nested
    @DisplayName("makeDeposit should")
    class MakeDepositShould{
        @Test
        public void call_transactions_to_be_added_in_log(){
            Transactions transactions = mock(Transactions.class);
            Account account = new Account(transactions);

            account.makeDeposit(new Amount(100));

            verify(transactions).add(new Amount(100));
        }
    }
}
