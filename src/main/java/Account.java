class Account {


    private final Transactions transactions;
    private final Operations operations;

    public Account(Transactions transactions, Operations operations) {

        this.transactions = transactions;
        this.operations = operations;
    }

    public void makeDeposit(Amount amount) {
        transactions.add(amount);
    }

    public void makeWithdrawal(Amount amount) {
        transactions.remove(amount);
    }
}
