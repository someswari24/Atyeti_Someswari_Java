package com.foodDeliveryApplication.repository;

import com.foodDeliveryApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
