package com.ftpl.EmployeePortal.dao;

import com.ftpl.EmployeePortal.entities.Employee;
import java.util.List;

public interface EmployeeDao {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void updateEmployee(Employee employee);
    boolean deleteById(Long id);
}
