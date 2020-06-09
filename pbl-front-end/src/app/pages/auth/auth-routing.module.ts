import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StudentLoginComponent} from './student-login/student-login.component';
import {StudentSignupComponent} from './student-signup/student-signup.component';
import {TeacherLoginComponent} from './teacher-login/teacher-login.component';
import {AdminLoginComponent} from "./admin-login/admin-login.component";


const routes: Routes = [
  { path: 'student/login', component: StudentLoginComponent},
  { path: 'student/signup', component: StudentSignupComponent},
  { path: 'teacher/login', component: TeacherLoginComponent},
  { path: 'admin/login', component: AdminLoginComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
