package onlineShoppingCart;

public class PercentageDiscount implements Discount{
    private double discount;

    public PercentageDiscount(double discount) {
        this.discount=discount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount-(totalAmount*(discount*0.01));
    }
}
