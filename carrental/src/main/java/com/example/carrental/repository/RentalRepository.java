package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Rental;
import com.example.carrental.model.User;
import com.example.carrental.model.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByUser(User user);
    List<Rental> findByCar(Car car);
    List<Rental> findByStatus(RentalStatus status);

    @Query("SELECT r FROM Rental r WHERE r.car = :car AND r.status IN ('BOOKED','ONGOING') " +
            "AND (:startDate <= r.endDate AND :endDate >= r.startDate)")
    List<Rental> findConflictingRentals(Car car, LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Rental r WHERE r.startDate BETWEEN :from AND :to OR r.endDate BETWEEN :from AND :to")
    List<Rental> findRentalsBetweenDates(LocalDate from, LocalDate to);
}
