import { Component, OnInit } from '@angular/core';
import { EmployeeServiceService } from '../employee-service.service';

@Component({
  selector: 'app-retrieve-employees',
  templateUrl: './retrieve-employees.component.html',
  styleUrls: ['./retrieve-employees.component.css']
})
export class RetrieveEmployeesComponent implements OnInit {

  employees: any[] = [];
  paginatedEmployees: any[] = [];
  currentPage: number = 1;
  pageSize: number = 5;
  totalPages: number = 1;

  constructor(private employeeService: EmployeeServiceService) { }

  ngOnInit(): void {
    this.fetchEmployees();
  }

  fetchEmployees() {
    this.employeeService.getAllEmployees().subscribe({
      next: (res: any) => {
        // Convert byte[] to Base64 string if photo exists
        this.employees = res.map((emp: any) => {
          if (emp.photo) {
            emp.photoBase64 = `data:image/png;base64,${emp.photo}`;
          }
          return emp;
        });
        this.totalPages = Math.ceil(this.employees.length / this.pageSize);
        this.setPage(1);
      },
      error: (err) => {
        console.error('Error fetching employees:', err);
      }
    });
  }

  setPage(page: number) {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
    const startIndex = (page - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedEmployees = this.employees.slice(startIndex, endIndex);
  }

  deleteEmployee(id: number) {
    if (!confirm('Are you sure you want to delete this employee?')) return;

    this.employeeService.deleteEmployee(id).subscribe({
      next: (res) => {
        alert(res);
        this.fetchEmployees(); // Refresh list
      },
      error: (err) => {
        console.error('Error deleting employee:', err);
        alert('Failed to delete employee.');
      }
    });
  }
}
