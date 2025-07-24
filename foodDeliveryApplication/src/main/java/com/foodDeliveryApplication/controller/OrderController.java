package com.foodDeliveryApplication.controller;

import com.foodDeliveryApplication.dto.OrderRequestDTO;
import com.foodDeliveryApplication.dto.OrderResponseDTO;
import com.foodDeliveryApplication.model.Order;
import com.foodDeliveryApplication.model.OrderStatus;
import com.foodDeliveryApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    public OrderResponseDTO place(@RequestBody OrderRequestDTO orderRequest) {
        return service.placeOrder(orderRequest);
    }

    @PutMapping("/{id}/status")
    public void update(@PathVariable Long id, @RequestParam OrderStatus status) {
        service.updateStatus(id, status);
    }

    @GetMapping("/customer/{id}")
    public List<Order> getByCustomer(@PathVariable Long id) {
        return service.getByCustomer(id);
    }

    @GetMapping("/restaurant/{id}")
    public List<Order> getByRestaurant(@PathVariable Long id) {
        return service.getByRestaurant(id);
    }
}

