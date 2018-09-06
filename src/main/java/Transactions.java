import java.util.ArrayList;

public interface Transactions {


    void add(Amount amount);

    void remove(Amount amount);

    ArrayList<Operations> getOperationsHistory();
}
