package com.example.carrental.repository;

import com.example.carrental.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Optional<Discount> findByCode(String code);

    @Query("SELECT d FROM Discount d WHERE d.expiryDate >= :today")
    List<Discount> findActiveDiscounts(LocalDate today);

}
