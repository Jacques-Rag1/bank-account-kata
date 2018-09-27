import account.*;
import api.InMemoryBalance;
import api.ConsoleTransactionsPrinter;
import api.RealClock;
import api.InMemoryTransactions;

public class Main {
    public static void main(String[] args){
        Amount balanceInit = new Amount(0);
        account.Balance balance = InMemoryBalance.with(balanceInit);

        Clock clock = new RealClock();
        Transactions transactions = new InMemoryTransactions(clock);

        TransactionsPrinter transactionsPrinter = new ConsoleTransactionsPrinter();

        Account account = new Account(transactions, transactionsPrinter, balance);

        account.makeDeposit(new Amount(500));
        account.makeDeposit(new Amount(340));
        account.makeWithdrawal(new Amount(200));
        account.makeWithdrawal(new Amount(80));
        account.makeDeposit(new Amount(50));

        account.showHistory();

    }
}
