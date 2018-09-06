class Account {


    private final Transactions transactions;
    private final OperationsHistory operationsHistory;
    private final OperationDate operationDate;

    public Account(Transactions transactions, OperationsHistory operationsHistory, OperationDate date) {
        this.transactions = transactions;
        this.operationsHistory = operationsHistory;
        this.operationDate = date;
    }


    public void makeDeposit(Amount amount) {
        transactions.add(new OperationStatement(Operations.DEPOSIT, amount, operationDate));
    }

    public void makeWithdrawal(Amount amount) {
        transactions.remove(new OperationStatement(Operations.WITHDRAWAL, amount, operationDate));
    }

    public void getOperationsHistory() {
        operationsHistory.setPrinting(transactions);
    }
}
