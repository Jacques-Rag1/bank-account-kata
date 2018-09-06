import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);

    TransactionsFake transactionsFake;
    OperationDateFake dateFake;
    Account account;

    @Before
    public void varInit(){
        transactionsFake = new TransactionsFake();
        dateFake = new OperationDateFake();
        account = new Account(transactionsFake, dateFake);
    }
    @Test
    public void make_deposit_should_delegate_to_transaction(){
        account.makeDeposit(new Amount(0));

        assertThat(transactionsFake.balance).isEqualTo(0);
    }
    @Test
    public void make_deposit_has_to_run_with_value(){

        account.makeDeposit(AMOUNT_100);

        assertThat(transactionsFake.balance).isEqualTo(100);
    }
    @Test
    public void make_deposit_can_aggregate_amount(){

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_100);

        assertThat(transactionsFake.balance).isEqualTo(200);
    }

    @Test
    public void makeWithdrawal_should_send_request_to_transactions_to_remove() {
        transactionsFake.balance = 250;

        account.makeWithdrawal(AMOUNT_50);

        assertThat(transactionsFake.balance).isEqualTo(200);
    }

    @Test
    public void getOperationsHistory_should_return_a_list_of_all_operations() {
        transactionsFake.balance = 0;


    }

    @Test
    public void history_with_date_and_amount() {
        transactionsFake.balance = 0;


    }

    class TransactionsFake implements Transactions{
        public int balance = 0;
        public List<OperationStatement> statements = new ArrayList<>();


        @Override
        public OperationStatement add(Amount amount, OperationDate operationDate) {
            balance += amount.value;
            return new OperationStatement(Operations.DEPOSIT, amount, operationDate, new Amount(balance));
        }
        @Override
        public OperationStatement remove(Amount amount, OperationDate operationDate) {
            balance -= amount.value;
            return new OperationStatement(Operations.WITHDRAWAL, amount, operationDate, new Amount(balance));
        }

        @Override
        public void addStatement(OperationStatement statement) {
            statements.add(statement);
        }

        @Override
        public void getOperationsHistory() {
            statements.forEach(System.out::println);
        }

    }

    class OperationDateFake implements OperationDate{

        @Override
        public LocalDate getDate() {
            return LocalDate.of(2018, 9, 06);
        }

        @Override
        public String toString() {
            return getDate().toString();
        }
    }
}
