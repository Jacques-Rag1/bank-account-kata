package utils;

public interface Balance {

    Amount getCurrentBalance();

    boolean addMoney(AmountPositive amount);

    boolean removeMoney(AmountPositive amount);

}
