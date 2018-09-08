import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final Amount AMOUNT_100 = new Amount(100);
    public static final Amount AMOUNT_50 = new Amount(50);
    public static final Amount AMOUNT_200 = new Amount(200);

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        final ArrayList<Amount> addAmounts = new ArrayList<>();
        addAmounts.add(AMOUNT_100);
        addAmounts.add(AMOUNT_50);
        addAmounts.add(AMOUNT_200);

        TransactionsFake transactionsFake = new TransactionsFake(
            addAmounts);
        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake);


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
        Account account = new Account(transactionsFake, operationsFake);

        account.makeWithdrawal(AMOUNT_100);
        account.makeWithdrawal(AMOUNT_50);
        account.makeWithdrawal(AMOUNT_200);

        transactionsFake.verifyRemove();
    }

    @Test
    public void when_a_deposit_is_made_Account_should_insert_in_operations() {
        TransactionsFake transactionsFake = new TransactionsFake(
            new ArrayList<Amount>());
        Map<String, Amount> depositsOperations = new HashMap<>();
        depositsOperations.put("DEPOSIT", AMOUNT_100);
        depositsOperations.put("DEPOSIT", AMOUNT_50);

        OperationsFake operationsFake = new OperationsFake(depositsOperations);
        Account account = new Account(transactionsFake, operationsFake);

        account.makeDeposit(AMOUNT_100);
        account.makeDeposit(AMOUNT_50);

        operationsFake.verifyCheck();
    }


    class TransactionsFake implements Transactions{

        List<Amount> AmountsExpected;
        List<Amount> addAmountsActual = new ArrayList<>();
        List<Amount> removeAmountsActual = new ArrayList<>();


        public TransactionsFake(List<Amount> addAmounts) {
            this.AmountsExpected = addAmounts;
        }
        public void verifyAdd(){
            assertThat(this.addAmountsActual).isEqualTo(this.AmountsExpected);
        }

        @Override
        public void add(Amount amount) {
           addAmountsActual.add(amount);
        }

        @Override
        public void remove(Amount amount) {
            removeAmountsActual.add(amount);
        }

        public void verifyRemove() {
            assertThat(this.removeAmountsActual).isEqualTo(this.AmountsExpected);
        }
    }
    class OperationsFake implements Operations{
        Map<String, Amount> depositsOperationsExpected;
        Map<String, Amount> depositsOperationsActual = new HashMap<>();

        public OperationsFake() {
        }

        public OperationsFake(Map<String, Amount> depositsOperationsExpected) {
            this.depositsOperationsExpected = depositsOperationsExpected;
        }

        public void verifyCheck() {
            assertThat(this.depositsOperationsActual).isEqualTo(this.depositsOperationsExpected);
        }

        @Override
        public void addStatement(String operation, Amount amount) {
            this.depositsOperationsActual.put(operation, amount);
        }
    }
}
