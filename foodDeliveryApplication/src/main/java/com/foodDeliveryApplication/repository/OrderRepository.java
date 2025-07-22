package com.foodDeliveryApplication.repository;

import com.foodDeliveryApplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
