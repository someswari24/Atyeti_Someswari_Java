package com.foodDeliveryApplication.controller;

import com.foodDeliveryApplication.dto.CustomerDTO;
import com.foodDeliveryApplication.model.Customer;
import com.foodDeliveryApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping
    public Customer register(@RequestBody CustomerDTO customer) {
        return service.register(customer);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }
}

