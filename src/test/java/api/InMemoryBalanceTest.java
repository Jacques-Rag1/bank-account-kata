package api;

import account.Amount;
import account.OperationType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryBalanceTest {

    @Test
    public void asAmount_should_return_the_current_balance(){

        final Amount amount = new Amount(10);
        final InMemoryBalance balance = InMemoryBalance.with(amount);

        final Amount actual = balance.asAmount();

        final Amount expected = new Amount(10);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void a_deposit_should_increase_the_balance(){

        final Amount amount = new Amount(10);
        final InMemoryBalance balance = InMemoryBalance.with(amount);

        balance.modify(OperationType.DEPOSIT, new Amount(20));

        final Amount expected = new Amount(30);
        assertThat(balance.asAmount()).isEqualTo(expected);
    }
    @Test
    public void a_withdrawal_should_decrease_the_balance(){

        final Amount amount = new Amount(50);
        final InMemoryBalance balance = InMemoryBalance.with(amount);

        balance.modify(OperationType.WITHDRAWAL, new Amount(20));

        final Amount expected = new Amount(30);
        assertThat(balance.asAmount()).isEqualTo(expected);
    }

}