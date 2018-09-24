package service;

import utils.Amount;
import utils.Operation;
import utils.Transactions;

public class TransactionService implements Transactions {

    public TransactionService() {
    }

    @Override
    public void add(Operation operation, Amount amount) {


        System.out.println("--> making transaction of " + operation + " with amount " + amount);
    }
}
