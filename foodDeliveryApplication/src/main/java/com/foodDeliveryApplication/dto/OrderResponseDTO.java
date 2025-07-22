package com.foodDeliveryApplication.dto;

import java.util.List;

public class OrderResponseDTO {
    public Long orderId;
    public String status;
    public double totalPrice;
    public List<String> items;
}

