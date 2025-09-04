package com.example.carrental.model;

import com.example.carrental.model.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@Table(name="rental")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Car car;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double baseCost;
    private Double discountApplied;
    private Double totalCost;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;
}
