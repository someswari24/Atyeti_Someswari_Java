package com.example.carrental.repository;

import com.example.carrental.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByBranchAndAvailableTrue(Branch branch);

    @Query("SELECT c FROM Car c WHERE lower(c.model) LIKE lower(concat('%', :keyword, '%')) " +
            "OR lower(c.brand) LIKE lower(concat('%', :keyword, '%'))")
    List<Car> searchByModelOrBrand(String keyword);
}
