class Account {


    private final Transactions transactions;
    private final OperationsHistory operationsHistory;

    public Account(Transactions transactions, OperationsHistory operationsHistory) {
        this.transactions = transactions;
        this.operationsHistory = operationsHistory;
    }


    public void makeDeposit(Amount amount) {
        transactions.add(amount);
    }

    public void makeWithdrawal(Amount amount) {
        transactions.remove(amount);
    }

    public void getOperationsHistory() {
        operationsHistory.setPrinting(transactions);
    }
}
