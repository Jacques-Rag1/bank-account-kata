import service.BalanceService;
import service.DateService;
import utils.Account;
import utils.AmountPositive;

public class Main {

    public static void main(String[] args){
        service.TransactionService transaction = new service.TransactionService();
        service.DateService date = new DateService();
        service.BalanceService balanceService = new BalanceService();
        utils.Account account = new Account(transaction, date);
        account.joinBalance(balanceService);

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
