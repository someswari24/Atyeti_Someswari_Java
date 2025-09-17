package com.employeeReactiveProgramming.controller;

import com.employeeReactiveProgramming.model.Employee;
import com.employeeReactiveProgramming.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee){
        System.out.println("save:-"+Thread.currentThread().hashCode());
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/all")
    public Flux<Employee>getAllEMployees(){
        System.out.println("all:-"+Thread.currentThread().hashCode());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getemp/{id}")
    public Mono<Employee>getEmployee(@PathVariable Integer id){
        System.out.println("get:-"+Thread.currentThread().hashCode());
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/remove/{id}")
    public Mono<Void>removeEmployee(@PathVariable Integer id){
        System.out.println("delete:-"+Thread.currentThread().hashCode());
        return employeeService.deleteEmployee(id);
    }
}
