package bankaccount.console;

import bankaccount.service.*;

public class MainExample {

    public static void main(String[] args){
        Amount balance = Amount.of(500);

        CurrentDate currentDate = new CurrentDate();
        Operation operation = new Operation();
        Transaction transaction = new Transaction(balance);

        Account myAccount = new Account(transaction, operation, currentDate);

        myAccount.makeDeposit(Amount.of(200));
        myAccount.makeDeposit(Amount.of(20));
        myAccount.makeWithdrawal(Amount.of(100));
        myAccount.makeDeposit(Amount.of(300));
        myAccount.makeWithdrawal(Amount.of(650));
        myAccount.makeWithdrawal(Amount.of(20));

        myAccount.showOperationsHistory();

    }
}
