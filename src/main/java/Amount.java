class Amount {
    int value;

    public Amount(int amountValue) {
        this.value = amountValue;
    }

    @Override
    public String toString() {
        return "Amount{" +
            "value=" + value +
            '}';
    }
}
