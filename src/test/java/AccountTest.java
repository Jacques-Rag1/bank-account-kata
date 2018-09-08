import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);
    public static final Amount AMOUNT_200 = new Amount(200);
    public static final Amount BALANCE_100 = new Amount(100);
    public static final Amount BALANCE_150 = new Amount(150);
    public static final LocalDate DAY_OF_OPERATIONS = LocalDate.of(2018, 9, 8);

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        final ArrayList<Amount> addAmounts = new ArrayList<>();
        addAmounts.add(AMOUNT_100);
        addAmounts.add(AMOUNT_50);
        addAmounts.add(AMOUNT_200);

        TransactionsFake transactionsFake = new TransactionsFake(
            addAmounts);
        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake, new CurrentDateFake(DAY_OF_OPERATIONS));


        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeDeposit(AMOUNT_200);

        transactionsFake.verifyAdd();
    }

    @Test
    public void make_withdrawal_should_delegate_to_transaction() {
        ArrayList<Amount> removeAmounts = new ArrayList<>();
        removeAmounts.add(AMOUNT_100);
        removeAmounts.add(AMOUNT_50);
        removeAmounts.add(AMOUNT_200);

        TransactionsFake transactionsFake = new TransactionsFake(
            removeAmounts);

        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake, new CurrentDateFake(DAY_OF_OPERATIONS));

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_200);

        transactionsFake.verifyRemove();
    }

    @Test
    public void when_a_deposit_is_made_Account_should_insert_in_operations() {
        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, new Amount(0), null));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, new Amount(0), null));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFake, operationsFake, new CurrentDateFake(DAY_OF_OPERATIONS));

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);

        operationsFake.verifyCheckWithOperationAndAmount();
    }

    @Test
    public void when_a_withdrawal_is_made_Account_should_insert_in_operations() {
        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_100, new Amount(0), null));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, new Amount(0), null));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFake, operationsFake, new CurrentDateFake(DAY_OF_OPERATIONS));

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheckWithOperationAndAmount();
    }
    @Test
    public void when_operations_are_made_Account_should_insert_in_operations_with_balance() {
        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, BALANCE_100, null));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, BALANCE_150, null));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, BALANCE_100, null));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFake, operationsFake, new CurrentDateFake(DAY_OF_OPERATIONS));

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheckWithOperationAndAmountAndBalance();
    }
    @Test
    public void when_operations_are_made_Account_should_insert_in_operations_with_date() {
        CurrentDateFake currentDateFake = new CurrentDateFake(DAY_OF_OPERATIONS);
        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, BALANCE_100, currentDateFake));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, BALANCE_150, currentDateFake));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, BALANCE_100, currentDateFake));

        OperationsFake operationsFake = new OperationsFake(statements);

        Account account = new Account(transactionsFake, operationsFake, currentDateFake);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheckWithOperationAndAmountAndBalanceAndDate();
    }



    class TransactionsFake implements Transactions{

        List<Amount> amountsExpected;
        List<Amount> addAmountsActual = new ArrayList<>();
        List<Amount> removeAmountsActual = new ArrayList<>();
        Amount balanceActual = new Amount(0);


        public TransactionsFake(List<Amount> addAmounts) {
            this.amountsExpected = addAmounts;
        }

        public void verifyAdd(){
            assertThat(this.addAmountsActual).isEqualTo(this.amountsExpected);
        }

        public void verifyRemove() {
            assertThat(this.removeAmountsActual).isEqualTo(this.amountsExpected);
        }

        @Override
        public Amount add(Amount amount) {
           addAmountsActual.add(amount);
           balanceActual.plus(amount);
           return balanceActual;
        }

        @Override
        public Amount remove(Amount amount) {
            removeAmountsActual.add(amount);
            balanceActual.minus(amount);
            return balanceActual;
        }

    }
    class OperationsFake implements Operations{
        List<OperationStatement> operationsExpected;
        List<OperationStatement> operationsActual = new ArrayList<>();

        public OperationsFake() {
        }

        public OperationsFake(List<OperationStatement> operationsExpected) {
            this.operationsExpected = operationsExpected;
        }

        public void verifyCheckWithOperationAndAmount() {
            this.operationsActual.forEach(statement -> statement.setBalance(new Amount(0)));
            this.operationsActual.forEach(statement -> statement.setCurrentDate(null));
            assertThat(this.operationsActual).isEqualTo(this.operationsExpected);
        }
        public void verifyCheckWithOperationAndAmountAndBalance() {
            this.operationsActual.forEach(statement -> statement.setCurrentDate(null));
            assertThat(this.operationsActual).isEqualTo(this.operationsExpected);
        }
        public void verifyCheckWithOperationAndAmountAndBalanceAndDate() {
            assertThat(this.operationsActual).isEqualTo(this.operationsExpected);
        }
        @Override
        public void addStatement(OperationStatement operationStatement) {
            this.operationsActual.add(operationStatement);
        }
    }
    class CurrentDateFake implements CurrentDate{
        LocalDate currentDate;

        public CurrentDateFake(LocalDate currentDate) {
            this.currentDate = currentDate;
        }

        @Override
        public String toString() {
            return "CurrentDateFake{" +
                    "currentDate=" + currentDate +
                    '}';
        }

        @Override
        public void getCurrentDate() {

        }
    }
}
