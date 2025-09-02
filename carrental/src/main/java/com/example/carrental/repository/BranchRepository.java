package com.example.carrental.repository;

import com.example.carrental.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Long> {
    @Query("SELECT b FROM Branch b WHERE lower(b.name) LIKE lower(concat('%', :name, '%'))")
    List<Branch> searchByName(String name);
}
