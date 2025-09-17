package com.employeeReactiveProgramming.service;

import com.employeeReactiveProgramming.model.Employee;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface EmployeeService {
    public Mono<Employee>saveEmployee(Employee emp);
    public Flux<Employee>getAllEmployees();
    public Mono<Employee>getEmployee(Integer id);
    public Mono<Void>deleteEmployee(Integer id);
}
