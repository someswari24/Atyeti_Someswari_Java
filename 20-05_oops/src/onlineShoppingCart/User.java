package onlineShoppingCart;

public class User {
    private String userId;
    private String userName;
    private String email;
    private Cart cart;

    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.cart=new Cart();
    }

    public void viewCart(){
        cart.viewItems();
    }

    public void removeCart(Product product){
        cart.removeItem(product);
    }

    public void addCart(Product product,int quantity){
        cart.addItem(product,quantity);
    }

    public void checkOutBill(Discount discount){
        double total=cart.calculateBill();
        double totalBillAfterDiscount=discount.applyDiscount(total);
        System.out.println("Total Bill:"+total);
        System.out.println("Total After Discount:"+totalBillAfterDiscount);
        cart.clearCart();
    }
}
