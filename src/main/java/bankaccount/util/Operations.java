package bankaccount.util;

import bankaccount.service.OperationStatement;

public interface Operations {

    void addStatement(OperationStatement operationStatement);

    void showAllStatements();
}
