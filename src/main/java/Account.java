class Account {


    private final Transactions transactions;
    private final Operations operations;

    public Account(Transactions transactions, Operations operations) {

        this.transactions = transactions;
        this.operations = operations;
    }

    public void makeDeposit(Amount amount) {
        transactions.add(amount);
        operations.addStatement(new OperationStatement("DEPOSIT", amount));
    }

    public void makeWithdrawal(Amount amount) {
        transactions.remove(amount);
        operations.addStatement(new OperationStatement("WITHDRAWAL", amount));
    }

}
