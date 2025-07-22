package com.foodDeliveryApplication.dto;

import java.util.List;

public class OrderRequestDTO {
    public Long customerId;
    public List<OrderItemRequestDTO> items;
}

