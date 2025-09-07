import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {
  private EMPLOYEE_PORTAL_API_URL = 'http://localhost:8080/employeePortal';

  constructor(private httpClient: HttpClient) {}

  // Get all employees
  getAllEmployees(): Observable<any> {
    return this.httpClient.get(`${this.EMPLOYEE_PORTAL_API_URL}/retrieveEmployees`);
  }

  // Add employee (using FormData for photo upload)
  addEmployee(employee: any, file?: File): Observable<any> {
    const formData = new FormData();
    formData.append('name', employee.name);
    formData.append('dob', employee.dob);
    formData.append('department', employee.department);
    formData.append('address', employee.address);
    if (file) {
      formData.append('photo', file);
    }

    return this.httpClient.post(`${this.EMPLOYEE_PORTAL_API_URL}/addEmployee`, formData, {
      responseType: 'text'
    });
  }

  // Delete employee
  deleteEmployee(id: number): Observable<any> {
    return this.httpClient.delete(`${this.EMPLOYEE_PORTAL_API_URL}/deleteEmployee/${id}`, {
      responseType: 'text'
    });
  }

  // Update employee (using FormData again for photo)
  updateEmployee(employee: any, file?: File): Observable<any> {
    const formData = new FormData();
    formData.append('id', employee.id);
    formData.append('name', employee.name);
    formData.append('dob', employee.dob);
    formData.append('department', employee.department);
    formData.append('address', employee.address);
    if (file) {
      formData.append('photo', file);
    }

    return this.httpClient.put(`${this.EMPLOYEE_PORTAL_API_URL}/updateEmployee`, formData, {
      responseType: 'text'
    });
  }
}
