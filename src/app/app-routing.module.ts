import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { HomeComponent } from './home/home.component';
import { RetrieveEmployeesComponent } from './retrieve-employees/retrieve-employees.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

const routes: Routes = [
  //Redirection Routing 
  {
    path:'', redirectTo: 'home' , pathMatch: 'full' 
  },
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'addEmployee', component: AddEmployeeComponent
  },
  {
    path: 'getEmployees', component: RetrieveEmployeesComponent
  },
  {
    path: 'editEmployee/:id', component: UpdateEmployeeComponent
  }

  //Wildcard Routing
  // {
  //   path: '**', component: PagenotfoundComponent
  // }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
