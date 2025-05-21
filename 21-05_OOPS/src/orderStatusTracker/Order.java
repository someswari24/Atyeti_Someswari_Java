package orderStatusTracker;

import java.util.UUID;

public class Order {
    private UUID orderId;
    private String customerName;
    private String product;
    private int quantity;
    private OrderStatus orderStatus;

    public Order(String customerName, String product,int quantity) {
        this.orderId=UUID.randomUUID();
        this.customerName = customerName;
        this.quantity = quantity;
        this.product = product;
        this.orderStatus=OrderStatus.PLACED;
    }

    public void updateStatus(OrderStatus newStatus){
        this.orderStatus=newStatus;
        System.out.println("order status is:"+orderStatus);
    }

    public void getStatus(){
        System.out.println(orderStatus);
    }

    public String displayOrderDetails() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                '}';
    }

}
