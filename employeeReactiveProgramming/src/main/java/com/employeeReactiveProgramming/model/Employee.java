package com.employeeReactiveProgramming.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "REACTIVE_EMPLOYEE")
public class Employee {
    @Id
    private Integer id;
    private String empName;
    private String empDept;
    private Double empSalary;
}
