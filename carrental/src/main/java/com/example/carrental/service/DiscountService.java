package com.example.carrental.service;

import com.example.carrental.model.Discount;
import com.example.carrental.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public Discount createDiscount(Discount discount){
        log.info("New Discount creating :"+discount.getPercent());
        return discountRepository.save(discount);
    }

    public Optional<Discount> findByCode(String code){
        return discountRepository.findByCode(code);
    }

    public List<Discount> findActiveDiscounts(){
        return discountRepository.findActiveDiscounts(LocalDate.now());
    }
}
