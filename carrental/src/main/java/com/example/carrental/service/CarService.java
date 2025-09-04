package com.example.carrental.service;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Car addCar(Car car){
        log.info("Adding Car :"+car.getModel());
        return carRepository.save(car);
    }

    public List<Car> findByBranchAndAvailableTrue(Branch branch){
        return carRepository.findByBranchAndAvailableTrue(branch);
    }

    public List<Car> searchByModelOrBrand(String keyword){
        return carRepository.searchByModelOrBrand(keyword);
    }

    public void updateAvailability(Long carId,boolean available){
        carRepository.findById(carId).ifPresent(car -> {
            car.setAvailable(available);
            carRepository.save(car);
        });
    }

    public Optional<Car> getCarById(Long id) {
        log.info("Fetching car by id:"+ id);
        return carRepository.findById(id);
    }

    public void deleteCar(Long id) {
        log.warn("Deleting car with id:"+id);
        carRepository.deleteById(id);
    }

    public List<Car>getAllCars(){
        return carRepository.findAll();
    }
}
