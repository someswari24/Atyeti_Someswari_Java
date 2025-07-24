package com.foodDeliveryApplication.service;

import com.foodDeliveryApplication.dto.CustomerDTO;
import com.foodDeliveryApplication.model.Customer;
import com.foodDeliveryApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer register(CustomerDTO customerDTO){
        Customer customer=new Customer();
        customer.setName(customerDTO.name);
        customer.setPhoneNumber(customerDTO.phone);

        return customerRepository.save(customer);
    }

    public Customer get(Long id){
        return customerRepository.findById(id).orElseThrow();
    }
}
