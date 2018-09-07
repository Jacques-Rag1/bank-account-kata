import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        final ArrayList<Amount> addAmounts = new ArrayList<>();
        addAmounts.add(new Amount(100));
        addAmounts.add(new Amount(50));
        addAmounts.add(new Amount(200));

        TransactionsFake transactionsFake = new TransactionsFake(
            addAmounts);
        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake);


        account.makeDeposit(new Amount(100));
        account.makeDeposit(new Amount(50));
        account.makeDeposit(new Amount(200));

        transactionsFake.verifyAdd();
    }

    @Test
    public void make_withdrawal_should_delegate_to_transaction() {
        ArrayList<Amount> removeAmounts = new ArrayList<>();
        removeAmounts.add(new Amount(100));
        removeAmounts.add(new Amount(50));
        removeAmounts.add(new Amount(200));

        TransactionsFake transactionsFake = new TransactionsFake(
            removeAmounts);

        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake);

        account.makeWithdrawal(new Amount(100));
        account.makeWithdrawal(new Amount(50));
        account.makeWithdrawal(new Amount(200));

        transactionsFake.verifyRemove();
    }

    @Test
    public void foo() {
        TransactionsFake transactionsFake = new TransactionsFake(
            new ArrayList<Amount>());
        OperationsFake operationsFake = new OperationsFake();
        Account account = new Account(transactionsFake, operationsFake);

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
            assertThat(this.AmountsExpected).isEqualTo(this.addAmountsActual);
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
            assertThat(this.AmountsExpected).isEqualTo(this.removeAmountsActual);
        }
    }
    class OperationsFake implements Operations{



        public void verifyCheck() {

        }
    }
}
