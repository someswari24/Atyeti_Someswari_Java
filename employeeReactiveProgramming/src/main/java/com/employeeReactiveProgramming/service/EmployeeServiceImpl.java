package com.employeeReactiveProgramming.service;

import com.employeeReactiveProgramming.model.Employee;
import com.employeeReactiveProgramming.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> saveEmployee(Employee emp) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employeeRepository.save(emp);
    }

    @Override
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Employee> getEmployee(Integer id) {
        return employeeRepository.findById(String.valueOf(id));
    }

    @Override
    public Mono<Void> deleteEmployee(Integer id) {
        return employeeRepository.deleteById(String.valueOf(id));
    }
}
