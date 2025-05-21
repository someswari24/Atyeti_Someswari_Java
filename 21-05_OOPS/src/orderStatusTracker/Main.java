package orderStatusTracker;

public class Main {
    public static void main(String[] args) {
        Order order=new Order("someswari","laptop",1);

        System.out.println(order.displayOrderDetails());
        order.updateStatus(OrderStatus.SHIPPED);
        order.getStatus();
        order.updateStatus(OrderStatus.DELIVERED);
        order.getStatus();
        System.out.println(order.displayOrderDetails());
    }
}
