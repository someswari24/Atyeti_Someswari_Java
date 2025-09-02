package com.example.carrental.repository;

import com.example.carrental.model.Invoice;
import com.example.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    Optional<Invoice> findByRental(Rental rental);

    @Query("SELECT i FROM Invoice i WHERE i.generatedDate BETWEEN :from AND :to")
    List<Invoice> findInvoicesBetween(LocalDateTime from, LocalDateTime to);
}
