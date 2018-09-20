package service;

import org.junit.jupiter.api.Test;
import utils.Amount;
import utils.Operation;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionServiceTest {

    @Test
    public void when_a_deposit_is_made_add_should_return_currentBalance_plus_deposit(){
        TransactionService transactionService = new TransactionService(new Amount(0));
        Amount balance = transactionService.add(Operation.DEPOSIT, new Amount(100));
        assertThat(balance).isEqualTo(new Amount(100));
    }
    @Test
    public void when_a_withdrawal_is_made_add_should_return_currentBalance_minus_deposit(){
        TransactionService transactionService = new TransactionService(new Amount(300));
        Amount balance = transactionService.add(Operation.WITHDRAWAL, new Amount(100));
        assertThat(balance).isEqualTo(new Amount(200));
    }
}
