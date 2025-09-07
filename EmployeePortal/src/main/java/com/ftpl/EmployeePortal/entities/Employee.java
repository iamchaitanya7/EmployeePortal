package com.ftpl.EmployeePortal.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    private String department;

    private String address;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    // No-args constructor
    public Employee() {}

    // All-args constructor
    public Employee(Long id, String name, LocalDate dob, String department, String address, byte[] photo) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
        this.address = address;
        this.photo = photo;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }
}
