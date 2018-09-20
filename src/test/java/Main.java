import service.DateService;
import utils.Account;
import utils.Amount;

public class Main {

    public static void main(String[] args){
        Amount balance = Amount.createAmount(0);
        service.TransactionService transaction = new service.TransactionService(balance);
        service.DateService date = new DateService();
        utils.Account account = new Account(transaction, date);

        account.makeDeposit(Amount.createAmount(500));
        account.makeDeposit(Amount.createAmount(200));
        account.makeDeposit(Amount.createAmount(50));

        account.makeWithdrawal(Amount.createAmount(100));
        account.makeWithdrawal(Amount.createAmount(10));
        account.makeWithdrawal(Amount.createAmount(150));

        System.out.println("\nHistory of operations : ");
        account.getHistory().forEach(System.out::println);
    }
}
