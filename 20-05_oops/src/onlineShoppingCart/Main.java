package onlineShoppingCart;

public class Main {
    public static void main(String[] args) {
        Product shirt=new Product("TShirt",400.0,"clothing");
        Product shoes=new Product("shoes",1500.0,"footwear");

        User user1=new User("u011","priya","priya@gmail.com");
        User user2=new User("U067","Mounika","mounika@gmail.com");

        user1.addCart(shirt,2);

        user2.addCart(shoes,1);
        user2.addCart(shirt,1);

        Discount discount=new PercentageDiscount(10.0);
        System.out.println("User1 Bill :");
        user1.checkOutBill(discount);

        System.out.println("User2 Bill :");
        user2.checkOutBill(discount);
    }
}
