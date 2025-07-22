package com.foodDeliveryApplication.repository;

import com.foodDeliveryApplication.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
