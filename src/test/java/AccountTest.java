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
    OperationsHistoryFake operationsHistoryFake;
    OperationDateFake dateFake;
    Account account;

    @Before
    public void varInit(){
        transactionsFake = new TransactionsFake();
        operationsHistoryFake = new OperationsHistoryFake();
        dateFake = new OperationDateFake();
        account = new Account(transactionsFake, operationsHistoryFake, dateFake);
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
        ArrayList<Operations> operations = new ArrayList<>() ;
        operations.add(Operations.DEPOSIT);
        operations.add(Operations.DEPOSIT);
        operations.add(Operations.WITHDRAWAL);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_100);
        account.getOperationsHistory();

        assertThat(operationsHistoryFake.operationsHistory).isEqualTo(operations);
    }

    @Test
    public void history_with_date_and_amount() {
        transactionsFake.balance = 0;
        ArrayList<OperationStatement> operationStatements = new ArrayList<>();
        operationStatements.add(new OperationStatement(Operations.DEPOSIT, AMOUNT_100, dateFake));
        operationStatements.add(new OperationStatement(Operations.DEPOSIT, AMOUNT_100, dateFake));
        operationStatements.add(new OperationStatement(Operations.WITHDRAWAL, AMOUNT_100, dateFake));

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_100);
        account.getOperationsHistory();

        assertThat(operationsHistoryFake.history).isEqualTo(operationStatements);
        assertThat(operationsHistoryFake.history).isEqualTo(operationStatements);

    }

    class TransactionsFake implements Transactions{
        public int balance = 0;

        public List<Operations> operationsHistory = new ArrayList<>();
        public  List<OperationStatement> operationStatements = new ArrayList<>();

        public TransactionsFake() {
        }

        @Override
        public void add(OperationStatement operationStatement) {
            balance += operationStatement.getAmount().value;
            operationsHistory.add(Operations.DEPOSIT);

            operationStatements.add(operationStatement);
        }
        @Override
        public void remove(OperationStatement operationStatement) {
            balance -= operationStatement.getAmount().value;
            operationsHistory.add(Operations.WITHDRAWAL);

            operationStatements.add(operationStatement);
        }

        @Override
        public ArrayList<OperationStatement> getOperationsHistory() {
            return (ArrayList<OperationStatement>) operationStatements;
        }
    }

    class OperationsHistoryFake implements OperationsHistory{
        public ArrayList<Operations> operationsHistory;
        public ArrayList<OperationStatement> history;

        @Override
        public void setPrinting(Transactions transactions) {
            operationsHistory = (ArrayList<Operations>) transactionsFake.operationsHistory;
            history = transactions.getOperationsHistory();
        }
    }

    class OperationDateFake implements OperationDate{

        @Override
        public LocalDate getDate() {
            return LocalDate.of(2018, 9, 06);
        }
    }
}
