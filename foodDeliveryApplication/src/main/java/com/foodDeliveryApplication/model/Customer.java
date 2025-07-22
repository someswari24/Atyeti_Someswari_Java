package com.foodDeliveryApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;

    @ManyToOne
    private List<Order> orders= new ArrayList<>();
}
