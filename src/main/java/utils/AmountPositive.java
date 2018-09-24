package utils;

public class AmountPositive extends Amount{


    private AmountPositive(int amount) {
        super(amount);
    }
    public static AmountPositive of(int value){
        if (value < 0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        return new AmountPositive(value);
    }


}
