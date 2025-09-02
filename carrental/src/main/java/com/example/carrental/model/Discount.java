package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name="discount")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double percent;
    private Double flatAmount;
    private LocalDate validFrom;
    private LocalDate validTo;
    private boolean active;
}
