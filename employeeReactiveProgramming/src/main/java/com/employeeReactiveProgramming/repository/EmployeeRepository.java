package com.employeeReactiveProgramming.repository;

import com.employeeReactiveProgramming.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> {
}
