package com.ftpl.EmployeePortal.controller;

import com.ftpl.EmployeePortal.entities.Employee;
import com.ftpl.EmployeePortal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/employeePortal")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // ➡️ Add Employee with image upload
    @PostMapping(value = "/addEmployee", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String registerEmployee(
            @RequestParam("name") String name,
            @RequestParam("dob") String dob,
            @RequestParam("department") String department,
            @RequestParam("address") String address,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        try {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setDob(Date.valueOf(dob).toLocalDate ( )); // Convert String → Date (yyyy-MM-dd format)
            employee.setDepartment(department);
            employee.setAddress(address);

            if (photo != null && !photo.isEmpty()) {
                employee.setPhoto(photo.getBytes());
            }

            employeeService.registerEmployee(employee);
            return "Employee Registered Successfully!";
        } catch (IOException e) {
            return "Error while uploading employee photo!";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }

    // ➡️ Retrieve All Employees
    @GetMapping("/retrieveEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // ➡️ Retrieve Employee by ID
    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // ➡️ Update Employee with image
    @PutMapping(value = "/updateEmployee", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String updateEmployee(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("dob") String dob,
            @RequestParam("department") String department,
            @RequestParam("address") String address,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                return "Employee not found!";
            }

            employee.setName(name);
            employee.setDob(Date.valueOf(dob).toLocalDate ( ));
            employee.setDepartment(department);
            employee.setAddress(address);

            if (photo != null && !photo.isEmpty()) {
                employee.setPhoto(photo.getBytes());
            }

            employeeService.updateEmployee(employee);
            return "Employee Updated Successfully!";
        } catch (IOException e) {
            return "Error while updating employee photo!";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }

    // ➡️ Delete Employee
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            boolean isDeleted = employeeService.deleteEmployeeById(id);
            if (isDeleted) {
                return "Employee deleted successfully!";
            }
            return "Employee not found!";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }
}
