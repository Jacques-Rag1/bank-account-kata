import java.util.ArrayList;

public interface Transactions {


    void add(OperationStatement operationStatement);

    void remove(OperationStatement operationStatement);

    ArrayList<OperationStatement> getOperationsHistory();
}
