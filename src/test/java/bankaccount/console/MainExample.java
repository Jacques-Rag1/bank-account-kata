package bankaccount.console;

import bankaccount.service.*;

public class MainExample {

    public static void main(String[] args){
        Amount balance = new Amount(500);

        CurrentDate currentDate = new CurrentDate();
        Operation operation = new Operation();
        Transaction transaction = new Transaction(balance);

        Account myAccount = new Account(transaction, operation, currentDate);

        myAccount.makeDeposit(new Amount(200));
        myAccount.makeDeposit(new Amount(20));
        myAccount.makeWithdrawal(new Amount(100));
        myAccount.makeDeposit(new Amount(300));
        myAccount.makeWithdrawal(new Amount(650));
        myAccount.makeWithdrawal(new Amount(20));

        myAccount.showOperationsHistory();

    }
}
