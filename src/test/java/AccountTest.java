import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void make_deposit_should_delegate_to_transaction(){
        final ArrayList<Amount> addAmounts = new ArrayList<>();
        addAmounts.add(new Amount(0));
        TransactionsFake transactionsFake = new TransactionsFake(
            addAmounts);
        Account account = new Account(transactionsFake);


        account.makeDeposit(new Amount(0));

        transactionsFake.verifyAdd();
    }


    class TransactionsFake implements Transactions{

        List<Amount> addAmountsExpected ;
        List<Amount> addAmountsActual = new ArrayList<>();


        public TransactionsFake(List<Amount> addAmounts) {
            this.addAmountsExpected = addAmounts;
        }
        public void verifyAdd(){
            assertThat(this.addAmountsExpected).isEqualTo(this.addAmountsActual);
        }

        @Override
        public void add(Amount amount) {
           addAmountsActual.add(amount);
        }

    }
}
