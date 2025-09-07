package com.ftpl.EmployeePortal.service;

import com.ftpl.EmployeePortal.entities.Employee;
import java.util.List;

public interface EmployeeService {
    void registerEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    boolean deleteEmployeeById(Long id);
    void updateEmployee(Employee employee);
}
