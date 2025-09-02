package com.example.carrental.repository;

import com.example.carrental.model.Payment;
import com.example.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRental(Rental rental);

    @Query("SELECT p FROM Payment p WHERE p.status = 'PENDING'")
    List<Payment> findPendingPayments();

    @Query("SELECT p FROM Payment p WHERE p.status = 'COMPLETED' AND p.amount >= :minAmount")
    List<Payment> findCompletedPaymentsAbove(double minAmount);
}
