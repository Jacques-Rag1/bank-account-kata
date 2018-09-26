import core.*;
import service.AccountBalanceService;
import service.AccountLogPrinterService;
import service.OperationDateService;
import service.TransactionsService;

public class Main {
    public static void main(String[] args){
        Amount balanceInit = new Amount(0);
        AccountBalance accountBalance = AccountBalanceService.with(balanceInit);

        OperationDate operationDate = new OperationDateService();
        Transactions transactions = new TransactionsService(operationDate);

        AccountLogPrinter accountLogPrinter = new AccountLogPrinterService();

        Account account = new Account(transactions, accountLogPrinter, accountBalance);

        account.makeDeposit(new Amount(500));
        account.makeDeposit(new Amount(340));
        account.makeWithdrawal(new Amount(200));
        account.makeWithdrawal(new Amount(80));
        account.makeDeposit(new Amount(50));

        account.showHistory();

    }
}
