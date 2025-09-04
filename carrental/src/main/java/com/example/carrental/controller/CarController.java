package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService carService;
    private final BranchRepository branchRepository;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @GetMapping("/branch/{branchId}/available")
    public ResponseEntity<List<Car>> getAvailableCarsByBranch(@PathVariable Long branchId) {
        return branchRepository.findById(branchId)
                .map(branch -> ResponseEntity.ok(carService.findByBranchAndAvailableTrue(branch)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Car> searchCars(@RequestParam String keyword) {
        return carService.searchByModelOrBrand(keyword);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<String> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {
        carService.updateAvailability(id, available);
        return ResponseEntity.ok("Car availability updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully");
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}
