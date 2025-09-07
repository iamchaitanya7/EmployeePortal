import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeServiceService } from '../employee-service.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {

  // Employee model
  employee = {
    name: '',
    dob: '',
    department: '',
    address: ''
  };

  // Selected file for photo upload
  selectedFile: File | undefined;

  constructor(private employeeService: EmployeeServiceService, private router: Router) { }

  // Capture selected file from input
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // Submit employee data with photo
  submitHandler() {
    if (!this.employee.name || !this.employee.dob || !this.employee.department || !this.employee.address) {
      alert('Please fill in all required fields.');
      return;
    }

    this.employeeService.addEmployee(this.employee, this.selectedFile).subscribe({
      next: (res) => {
        alert('Employee added successfully!');
        this.router.navigate(['/retrieveEmployees']);
      },
      error: (error) => {
        console.error('Error adding employee:', error);
        alert('Failed to add employee. Please try again.');
      }
    });
  }
}
