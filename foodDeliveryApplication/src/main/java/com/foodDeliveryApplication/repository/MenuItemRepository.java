package com.foodDeliveryApplication.repository;

import com.foodDeliveryApplication.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
}
