package com.foodDeliveryApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    private Restaurant restaurant;

}
