package com.example.carrental.model;

import com.example.carrental.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    private Double amount;
    private String method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime timestamp= LocalDateTime.now();;

}
