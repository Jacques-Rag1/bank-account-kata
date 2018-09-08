package bankaccount.service;

import bankaccount.util.CurrentDates;
import bankaccount.util.Operations;
import bankaccount.util.OperationsType;
import bankaccount.util.Transactions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);
    public static final Amount AMOUNT_200 = new Amount(200);
    public static final Amount BALANCE_100 = new Amount(100);
    public static final Amount BALANCE_150 = new Amount(150);
    public static final LocalDate DAY_OF_OPERATIONS = LocalDate.of(2018, 9, 8);
    public static final LocalDate DAY_OF_OPERATIONS_PLUS_A_DAY = LocalDate.of(2018, 9, 8).plusDays(1);
    public static final LocalDate DAY_OF_OPERATIONS_MINUS_A_DAY = LocalDate.of(2018, 9, 8).minusDays(1);

    TransactionsFake transactionsFakeGeneric;
    OperationsFake operationsFakeGeneric;
    CurrentDatesFake currentDateFakeUseless;

    @Before
    public void initialise(){
        transactionsFakeGeneric = new TransactionsFake(
                new ArrayList<>());
        transactionsFakeGeneric.noBalance = true;
        currentDateFakeUseless = new CurrentDatesFake(DAY_OF_OPERATIONS);
        currentDateFakeUseless.methodCall = 3;
        operationsFakeGeneric = new OperationsFake();
    }
    @Test
    public void make_deposit_should_delegate_to_transaction(){
        final ArrayList<Amount> addAmounts = new ArrayList<>();
        addAmounts.add(AMOUNT_100);
        addAmounts.add(AMOUNT_50);
        addAmounts.add(AMOUNT_200);
        TransactionsFake transactionsFake = new TransactionsFake(
            addAmounts);

        Account account = new Account(transactionsFake, operationsFakeGeneric, currentDateFakeUseless);

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

        Account account = new Account(transactionsFake, operationsFakeGeneric, currentDateFakeUseless);

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_200);

        transactionsFake.verifyRemove();
    }

    @Test
    public void when_a_deposit_is_made_Account_should_insert_in_operations() {
        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, new Amount(0), currentDateFakeUseless));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, new Amount(0), currentDateFakeUseless));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFakeGeneric, operationsFake, currentDateFakeUseless);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);

        operationsFake.verifyCheck();
    }

    @Test
    public void when_a_withdrawal_is_made_Account_should_insert_in_operations() {
        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_100, new Amount(0), currentDateFakeUseless));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, new Amount(0), currentDateFakeUseless));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFakeGeneric, operationsFake, currentDateFakeUseless);

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheck();
    }
    @Test
    public void when_operations_are_made_Account_should_insert_in_operations_with_balance() {
        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, BALANCE_100, currentDateFakeUseless));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, BALANCE_150, currentDateFakeUseless));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, BALANCE_100, currentDateFakeUseless));

        OperationsFake operationsFake = new OperationsFake(statements);
        Account account = new Account(transactionsFake, operationsFake, currentDateFakeUseless);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheck();
    }
    @Test
    public void when_operations_are_made_Account_should_insert_in_operations_with_date() {
        CurrentDatesFake currentDateFake = new CurrentDatesFake(DAY_OF_OPERATIONS);
        CurrentDatesFake currentDateFakePlusOne = new CurrentDatesFake(DAY_OF_OPERATIONS_PLUS_A_DAY);
        CurrentDatesFake currentDateFakeMinusOne = new CurrentDatesFake(DAY_OF_OPERATIONS_MINUS_A_DAY);

        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, BALANCE_100, currentDateFake));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, BALANCE_150, currentDateFakePlusOne));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, BALANCE_100, currentDateFakeMinusOne));

        OperationsFake operationsFake = new OperationsFake(statements);

        Account account = new Account(transactionsFake, operationsFake, currentDateFake);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_50);

        operationsFake.verifyCheck();
    }
    @Test
    public void showOperationsHistory_should_delegate_to_Operations() {
        CurrentDatesFake currentDateFake = new CurrentDatesFake(DAY_OF_OPERATIONS);
        CurrentDatesFake currentDateFakePlusOne = new CurrentDatesFake(DAY_OF_OPERATIONS_PLUS_A_DAY);
        CurrentDatesFake currentDateFakeMinusOne = new CurrentDatesFake(DAY_OF_OPERATIONS_MINUS_A_DAY);

        TransactionsFake transactionsFake = new TransactionsFake(
                new ArrayList<>());

        List<OperationStatement> statements = new ArrayList<>();
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_100, BALANCE_100, currentDateFake));
        statements.add(new OperationStatement(OperationsType.DEPOSIT, AMOUNT_50, BALANCE_150, currentDateFakePlusOne));
        statements.add(new OperationStatement(OperationsType.WITHDRAWAL, AMOUNT_50, BALANCE_100, currentDateFakeMinusOne));

        OperationsFake operationsFake = new OperationsFake(statements);

        Account account = new Account(transactionsFake, operationsFake, currentDateFake);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_50);

        account.showOperationsHistory();

        operationsFake.verifyHistory();
    }




    class TransactionsFake implements Transactions {

        List<Amount> amountsExpected;
        List<Amount> addAmountsActual = new ArrayList<>();
        List<Amount> removeAmountsActual = new ArrayList<>();
        Amount balanceActual = new Amount(0);
        boolean noBalance = false;


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
           return noBalance ? new Amount(0) : balanceActual;
        }

        @Override
        public Amount remove(Amount amount) {
            removeAmountsActual.add(amount);
            balanceActual.minus(amount);
            return noBalance ? new Amount(0) : balanceActual;
        }

    }
    class OperationsFake implements Operations {
        List<OperationStatement> operationsExpected;
        List<OperationStatement> operationsActual = new ArrayList<>();
        int methodShowAllStatementsCall = 0;

        public OperationsFake() {
        }

        public OperationsFake(List<OperationStatement> operationsExpected) {
            this.operationsExpected = operationsExpected;
        }

        public void verifyCheck() {
           assertThat(this.operationsActual).isEqualTo(this.operationsExpected);
        }
        public void verifyHistory() {
            assertThat(methodShowAllStatementsCall).isEqualTo(1);
        }

        @Override
        public void addStatement(OperationStatement operationStatement) {
            this.operationsActual.add(operationStatement);
        }

        @Override
        public void showAllStatements() {
            methodShowAllStatementsCall++;
        }

    }
    class CurrentDatesFake implements CurrentDates {
        LocalDate currentDate;
        int methodCall = 0;
        public CurrentDatesFake(LocalDate currentDate) {
            this.currentDate = currentDate;
        }

        @Override
        public String toString() {
            return "CurrentDateFake{" +
                    "currentDate=" + currentDate +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CurrentDatesFake that = (CurrentDatesFake) o;
            return methodCall == that.methodCall &&
                    Objects.equals(currentDate, that.currentDate);
        }

        @Override
        public int hashCode() {

            return Objects.hash(currentDate, methodCall);
        }

        @Override
        public CurrentDates getCurrentDate() {
            if (methodCall == 0){
                methodCall++;
                return this;
            }
            if (methodCall == 1){
                methodCall++;
                return new CurrentDatesFake(DAY_OF_OPERATIONS_PLUS_A_DAY);
            }
            if (methodCall == 2){
                return new CurrentDatesFake(DAY_OF_OPERATIONS_MINUS_A_DAY);
            }
            return this;
        }
    }
}
