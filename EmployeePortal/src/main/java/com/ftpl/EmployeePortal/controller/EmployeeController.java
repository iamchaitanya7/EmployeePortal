package com.ftpl.EmployeePortal.controller;

import com.ftpl.EmployeePortal.entities.Employee;
import com.ftpl.EmployeePortal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:4200")

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public String registerEmployee (@RequestBody Employee employee) {
        int status = employeeService.registerEmployee (employee);
        switch (status){
            case 1:
                return "Employee Registered Successfully..!";
            case 2:
                return "Employee Already Exists.!";
            case 3:
                return "Something Error Occured.!";
            default:
                return "Error!";
        }
    }


    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees () {
        List<Employee> employees = employeeService.getAllEmployees ( );
        return employees;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee (@PathVariable int id) {
        boolean isDeleted = employeeService.deleteEmployeeById (id);
        try {
            if (isDeleted) {
                return "Employee deleted successfully!";
            }
            return "Employee not found!";
        }
        catch (Exception e) {
            return "Something Error Occured!";
        }
    }

    @PutMapping("/updateEmployee")
    public String updateStudent(@RequestBody Employee employee) {
        boolean updatedEmployee = employeeService.updateEmployee (employee);
        try {
            if (updatedEmployee) {
                return "Employee Details Updated Successfully";
            } else {
                return "Employee Updation Failed or Employee Does not Exist!";
            }
        } catch (Exception e) {
            return "Something Error Occured!";
        }

    }
}
