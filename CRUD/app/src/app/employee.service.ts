import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/api/v1/employees';
  constructor(private HttpClient: HttpClient) { 
    
  }

    getEmployeesList(): Observable<Employee[]>{
      return this.HttpClient.get<Employee[]>(`${this.baseUrl}`);
    }

    createEmployee(employee: Employee): Observable<Object>{
      return this.HttpClient.post(`${this.baseUrl}` , employee);
    }
}
