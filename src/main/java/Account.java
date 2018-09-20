class Account {
    private Transactions transaction;

    Account(Transactions transaction) {
        this.transaction = transaction;
    }

    public void makeDeposit(Amount amount) {
        transaction.add(amount);
    }

}
