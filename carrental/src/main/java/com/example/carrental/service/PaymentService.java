package com.example.carrental.service;

import com.example.carrental.model.Payment;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment){
        log.info("Making Payment :"+payment.getMethod());
        return paymentRepository.save(payment);
    }

    public Optional<Payment> findByRental(Rental rental){
        return paymentRepository.findByRental(rental);
    }

    public List<Payment> findPendingPayments(){
        return paymentRepository.findPendingPayments();
    }

    public List<Payment> findCompletedPaymentsAbove(double minAmount){
        return paymentRepository.findCompletedPaymentsAbove(minAmount);
    }
}
