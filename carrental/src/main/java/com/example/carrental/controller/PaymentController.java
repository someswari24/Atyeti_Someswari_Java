package com.example.carrental.controller;

import com.example.carrental.model.Payment;
import com.example.carrental.model.Rental;
import com.example.carrental.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.makePayment(payment);
        log.info("Payment made with ID: {}", savedPayment.getId());
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping("/rental/{rentalId}")
    public ResponseEntity<Payment> getPaymentByRental(@PathVariable Long rentalId) {
        Rental rental = new Rental();
        rental.setId(rentalId);
        Optional<Payment> payment = paymentService.findByRental(rental);

        return payment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pending")
    public List<Payment> getPendingPayments() {
        return paymentService.findPendingPayments();
    }

    @GetMapping("/completed/{minAmount}")
    public List<Payment> getCompletedPaymentsAbove(@PathVariable double minAmount) {
        return paymentService.findCompletedPaymentsAbove(minAmount);
    }
}
