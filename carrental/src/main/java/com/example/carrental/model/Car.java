package com.example.carrental.model;

import com.example.carrental.model.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="cars")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Double pricePerDay;

    @ManyToOne
    private Branch branch;

    private boolean available=true;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
}
