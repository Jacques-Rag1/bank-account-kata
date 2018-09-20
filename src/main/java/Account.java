class Account {
    private Transactions transaction;

    Account(Transactions transaction) {
        this.transaction = transaction;
    }

    public void makeDeposit(Amount amount) {
        makeOperation(Operation.DEPOSIT, amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(Operation.WITHDRAWAL, amount);
    }

    private void makeOperation(Operation operation, Amount amount) {
        transaction.add(operation, amount);
    }
}
