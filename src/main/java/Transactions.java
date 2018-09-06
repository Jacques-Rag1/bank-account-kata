public interface Transactions {


    OperationStatement add(Amount amount, OperationDate operationDate);

    OperationStatement remove(Amount amount, OperationDate operationDate);

    void addStatement(OperationStatement statement);

    void getOperationsHistory();
}
