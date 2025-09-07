package com.ftpl.EmployeePortal.service;

import com.ftpl.EmployeePortal.dao.EmployeeDao;
import com.ftpl.EmployeePortal.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int registerEmployee(Employee employee) {
        return employeeDao.saveEmployee (employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

}

