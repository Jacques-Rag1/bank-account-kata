class Account {


    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void makeDeposit(Amount amount) {
        transactions.add(amount);
    }

    public void makeWithdrawal(Amount amount) {
        transactions.remove(amount);
    }
}
