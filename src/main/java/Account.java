class Account {
    private Transactions transaction;

    Account(Transactions transaction) {
        this.transaction = transaction;
    }

    public void makeDeposit(int amount) {
        transaction.add(amount);
    }
}
