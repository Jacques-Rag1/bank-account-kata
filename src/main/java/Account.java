import java.util.ArrayList;

class Account {


    private final Transactions transactions;
    private final OperationDate operationDate;


    public Account(Transactions transactions, OperationDate date) {
        this.transactions = transactions;
        this.operationDate = date;
    }


    public void makeDeposit(Amount amount) {
        OperationStatement statement = transactions.add(amount, operationDate);
        transactions.addStatement(statement);

    }

    public void makeWithdrawal(Amount amount) {
        OperationStatement statement = transactions.remove(amount, operationDate);
        transactions.addStatement(statement);
    }

    public void printAllStatements() {
        transactions.getOperationsHistory();
    }
}
