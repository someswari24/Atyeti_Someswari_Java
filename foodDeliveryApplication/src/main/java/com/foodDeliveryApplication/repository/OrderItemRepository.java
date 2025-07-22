package com.foodDeliveryApplication.repository;

import com.foodDeliveryApplication.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
