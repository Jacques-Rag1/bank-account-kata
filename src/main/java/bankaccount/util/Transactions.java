package bankaccount.util;

import bankaccount.service.Amount;

public interface Transactions {


    Amount add(Amount amount);

    Amount remove(Amount amount);
}
