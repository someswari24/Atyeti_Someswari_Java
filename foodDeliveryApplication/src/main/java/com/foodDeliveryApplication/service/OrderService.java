package com.foodDeliveryApplication.service;

import com.foodDeliveryApplication.dto.OrderItemRequestDTO;
import com.foodDeliveryApplication.dto.OrderRequestDTO;
import com.foodDeliveryApplication.dto.OrderResponseDTO;
import com.foodDeliveryApplication.model.*;
import com.foodDeliveryApplication.repository.CustomerRepository;
import com.foodDeliveryApplication.repository.MenuItemRepository;
import com.foodDeliveryApplication.repository.OrderItemRepository;
import com.foodDeliveryApplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequest){
        Customer customer = customerRepository.findById(orderRequest.customerId)
                .orElseThrow();

        Order order=new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PLACED);
        orderRepository.save(order);

        double total=0;
        List<String> itemList=new ArrayList<>();

        for (OrderItemRequestDTO item:orderRequest.items){
            MenuItem menuItem = menuItemRepository.findById(item.menuItemId).orElseThrow();
            OrderItem orderItem=new OrderItem();

            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(item.quantity);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);

            total += item.quantity * menuItem.getPrice();
            itemList.add(menuItem.getName() + " x" + item.quantity);
        }
        order.setTotalPrice(total);
        orderRepository.save(order);

        OrderResponseDTO orderResponse=new OrderResponseDTO();
        orderResponse.orderId= order.getId();
        orderResponse.status=order.getStatus().name();
        orderResponse.totalPrice=total;
        orderResponse.items=itemList;

        return orderResponse;
    }

    public void updateStatus(Long orderId,OrderStatus status){
        Order order=orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<Order> getByCustomer(Long id){
        return orderRepository.findAll().stream().filter(order -> order.getCustomer().getId().equals(id)).toList();
    }

    public List<Order> getByRestaurant(Long restaurantId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getItems().stream()
                        .allMatch(orderItem -> orderItem.getMenuItem().getRestaurant().getId().equals(restaurantId)))
                .toList();
    }

}
