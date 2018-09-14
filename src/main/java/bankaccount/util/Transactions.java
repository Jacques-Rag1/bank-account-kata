package bankaccount.util;

import bankaccount.service.Amount;

public interface Transactions {


    Amount addAmount(Amount amount);

    Amount removeAmount(Amount amount);
}
