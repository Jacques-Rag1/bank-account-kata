import service.DateService;
import utils.Account;
import utils.Amount;
import utils.AmountPositive;

public class Main {

    public static void main(String[] args){
        Amount balance = Amount.of(0);
        service.TransactionService transaction = new service.TransactionService(balance);
        service.DateService date = new DateService();
        utils.Account account = new Account(transaction, date);

        account.makeDeposit(AmountPositive.of(500));
        account.makeDeposit(AmountPositive.of(200));
        account.makeDeposit(AmountPositive.of(50));

        account.makeWithdrawal(AmountPositive.of(100));
        account.makeWithdrawal(AmountPositive.of(10));
        account.makeWithdrawal(AmountPositive.of(1500));

        System.out.println("\nHistory of operations : ");
        account.getHistory().forEach(System.out::println);
    }
}
