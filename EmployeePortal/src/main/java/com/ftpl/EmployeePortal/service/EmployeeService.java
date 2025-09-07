package com.ftpl.EmployeePortal.service;

import com.ftpl.EmployeePortal.entities.Employee;

import java.util.List;

public interface EmployeeService {
    int registerEmployee (Employee employee);
    List<Employee> getAllEmployees ();
    boolean deleteEmployeeById (int id);
    boolean updateEmployee (Employee employee);
}
