package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.model.Rental;
import com.example.carrental.model.User;
import com.example.carrental.model.enums.RentalStatus;
import com.example.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
@Slf4j
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        Rental savedRental = rentalService.createRental(rental);
        log.info("Rental created with ID: {}", savedRental.getId());
        return ResponseEntity.ok(savedRental);
    }

    @GetMapping("/user/{userId}")
    public List<Rental> getRentalsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return rentalService.findByUser(user);
    }

    @GetMapping("/car/{carId}")
    public List<Rental> getRentalsByCar(@PathVariable Long carId) {
        Car car = new Car();
        car.setId(carId);
        return rentalService.findByCar(car);
    }

    @GetMapping("/status/{status}")
    public List<Rental> getRentalsByStatus(@PathVariable RentalStatus status) {
        return rentalService.findByStatus(status);
    }

    @GetMapping("/between")
    public List<Rental> getRentalsBetweenDates(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return rentalService.findRentalsBetweenDates(fromDate, toDate);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }
}
