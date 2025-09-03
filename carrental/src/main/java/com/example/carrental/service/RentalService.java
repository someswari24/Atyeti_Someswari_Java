package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Rental;
import com.example.carrental.model.User;
import com.example.carrental.model.enums.RentalStatus;
import com.example.carrental.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    @Transactional
    public Rental createRental(Rental rental){
        List<Rental>conflicts=rentalRepository.findConflictingRentals(
                rental.getCar(),rental.getStartDate(),rental.getEndDate()
        );
        if (!conflicts.isEmpty()){
            throw new RuntimeException("Car already booked for the given dates");
        }
        rental.setStatus(RentalStatus.BOOKED);
        return rentalRepository.save(rental);
    }

    public List<Rental> findByUser(User user){
        return rentalRepository.findByUser(user);
    }
    public List<Rental> findByCar(Car car){
        return rentalRepository.findByCar(car);
    }
    public List<Rental> findByStatus(RentalStatus status){
        return rentalRepository.findByStatus(status);
    }

    public List<Rental> findRentalsBetweenDates(LocalDate from, LocalDate to){
        return rentalRepository.findRentalsBetweenDates(from,to);
    }

    public List<Rental>getAllRentals(){
        return rentalRepository.findAll();
    }
}
