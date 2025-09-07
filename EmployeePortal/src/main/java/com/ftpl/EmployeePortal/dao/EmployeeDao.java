package com.ftpl.EmployeePortal.dao;

import com.ftpl.EmployeePortal.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    int saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    boolean updateEmployee(Employee employee);
    boolean deleteById(int id);
}
