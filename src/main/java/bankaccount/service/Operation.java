package bankaccount.service;

import bankaccount.util.Operations;

import java.util.ArrayList;
import java.util.List;

public class Operation implements Operations {
    private List<OperationStatement> operationStatements;

    public Operation() {
        operationStatements = new ArrayList<>();
    }

    @Override
    public void addStatement(OperationStatement operationStatement) {
        this.operationStatements.add(operationStatement);
    }

    @Override
    public void showAllStatements() {
        ServicePrinter.print(new ArrayList(operationStatements));
    }
}
