package EmployeeIdGenerator;

import java.util.UUID;

public class Employee {
    private String employeeName;
    private int employeeId;
    private static int idCounter = 1000;
    public final String companyName = "TechCorp";

    public Employee(String employeeName) {
        this.employeeName = employeeName;
        this.employeeId = idCounter++;

    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Employee.idCounter = idCounter;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId=" + employeeId +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}