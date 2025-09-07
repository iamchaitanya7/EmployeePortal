import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeServiceService } from '../employee-service.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  employee: any = {
    id: null,
    name: '',
    dob: '',
    department: '',
    address: '',
    photoBase64: '' // for displaying current photo
  };

  selectedFile: File | undefined;

  constructor(
    private employeeService: EmployeeServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const employeeId = this.route.snapshot.paramMap.get('id');
    if (employeeId) {
      this.getEmployeeDetails(+employeeId);
    }
  }

  getEmployeeDetails(id: number) {
    this.employeeService.getAllEmployees().subscribe((employees: any) => {
      const emp = employees.find((e: any) => e.id === id);
      if (emp) {
        this.employee = { ...emp };
      }
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];

    // Optional: show preview of the new selected photo
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.employee.photoBase64 = e.target.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  updateHandler() {
    this.employeeService.updateEmployee(this.employee, this.selectedFile).subscribe({
      next: () => {
        alert('Employee updated successfully!');
        this.router.navigate(['/retrieveEmployees']);
      },
      error: (error) => {
        console.error('Error updating employee:', error);
        alert('Failed to update employee. Please try again.');
      }
    });
  }
}
