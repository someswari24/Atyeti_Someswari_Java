package onlineShoppingCart;

public class NoDiscount implements Discount{
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}
