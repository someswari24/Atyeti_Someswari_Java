package onlineShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> cartItems=new HashMap<>();

    public void addItem(Product product,int quantity){
        cartItems.put(product,quantity);
    }

    public void removeItem(Product product){
        cartItems.remove(product);
    }

    public  void clearCart(){
        cartItems.clear();
    }

    public double calculateBill(){
        double total=0;
        for(Map.Entry<Product,Integer> entry:cartItems.entrySet()){
            total+=entry.getKey().getPrice()* entry.getValue();
        }
        return total;
    }

    public void viewItems() {
        if(cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        }
        else {
            System.out.println("Cart items:");
            for(Map.Entry<Product,Integer>entry:cartItems.entrySet()) {
                System.out.println(entry.getKey().toString()+", Quantity: "+entry.getValue());
            }
        }
    }
}
