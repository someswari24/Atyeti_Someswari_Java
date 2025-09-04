package com.example.carrental.controller;

import com.example.carrental.model.Discount;
import com.example.carrental.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Slf4j
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount savedDiscount = discountService.createDiscount(discount);
        log.info("Created discount with code: "+savedDiscount.getId());
        return ResponseEntity.created(URI.create("/discounts/" + savedDiscount.getId()))
                .body(savedDiscount);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Discount> findByCode(@PathVariable String code) {
        Optional<Discount> discount = discountService.findByCode(code);
        if (discount.isPresent()) {
            return ResponseEntity.ok(discount.get());
        } else {
            log.warn("Discount with code {} not found", code);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/active")
    public List<Discount> findActiveDiscounts() {
        return discountService.findActiveDiscounts();
    }
}
