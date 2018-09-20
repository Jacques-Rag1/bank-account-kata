class Account {
    private Transactions transaction;

    Account(Transactions transaction) {
        this.transaction = transaction;
    }

    public void makeDeposit(Amount amount) {
        makeOperation(amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(amount);
    }

    private void makeOperation(Amount amount) {
        transaction.add(amount);
    }
}
