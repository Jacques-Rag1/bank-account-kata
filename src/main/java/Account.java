class Account {


    private final Transactions transactions;
    private final Operations operations;

    public Account(Transactions transactions, Operations operations) {

        this.transactions = transactions;
        this.operations = operations;
    }

    public void makeDeposit(Amount amount) {
        makeOperation(OperationsType.DEPOSIT, amount);
    }

    public void makeWithdrawal(Amount amount) {
        makeOperation(OperationsType.WITHDRAWAL, amount);
    }
    private void makeOperation(OperationsType operationsType, Amount amount){
        if (operationsType.equals(OperationsType.DEPOSIT)){
            transactions.add(amount);
        }
        if (operationsType.equals(OperationsType.WITHDRAWAL)){
            transactions.remove(amount);
        }
        operations.addStatement(new OperationStatement(operationsType, amount));
    }

}
