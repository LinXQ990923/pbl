import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { StudentLoginComponent } from './student-login/student-login.component';
import { StudentSignupComponent } from './student-signup/student-signup.component';
import { TeacherLoginComponent } from './teacher-login/teacher-login.component';
import {
  NzAlertModule,
  NzButtonModule,
  NzCheckboxModule,
  NzFormModule,
  NzIconModule,
  NzInputModule
} from "ng-zorro-antd";
import {ReactiveFormsModule} from "@angular/forms";
import { AdminLoginComponent } from './admin-login/admin-login.component';


@NgModule({
  declarations: [StudentLoginComponent, StudentSignupComponent, TeacherLoginComponent, AdminLoginComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    NzFormModule,
    NzInputModule,
    NzAlertModule,
    ReactiveFormsModule,
    NzButtonModule,
    NzIconModule,
    NzCheckboxModule
  ],
  exports: [
    StudentLoginComponent,
    StudentSignupComponent,
    TeacherLoginComponent
  ]
})
export class AuthModule { }
