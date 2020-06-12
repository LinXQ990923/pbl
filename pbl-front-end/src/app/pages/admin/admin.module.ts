import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminMainComponent } from './admin-main/admin-main.component';
import {
  NzAvatarModule,
  NzBreadCrumbModule, NzButtonModule, NzDatePickerModule, NzDividerModule,
  NzFormModule,
  NzIconModule, NzInputModule,
  NzLayoutModule,
  NzMenuModule, NzSelectModule, NzTableModule, NzUploadModule
} from "ng-zorro-antd";
import { CourseCreateComponent } from './course-create/course-create.component';
import { AdminStudentsComponent } from './admin-students/admin-students.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AdminTeachersComponent } from './admin-teachers/admin-teachers.component';
import { CourseListComponent } from './course-list/course-list.component';


@NgModule({
  declarations: [AdminMainComponent, CourseCreateComponent, AdminStudentsComponent, AdminTeachersComponent, CourseListComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NzLayoutModule,
    NzAvatarModule,
    NzMenuModule,
    NzIconModule,
    NzBreadCrumbModule,
    NzFormModule,
    ReactiveFormsModule,
    NzInputModule,
    NzButtonModule,
    NzDividerModule,
    NzTableModule,
    NzDatePickerModule,
    NzUploadModule,
    NzSelectModule,
    FormsModule
  ]
})
export class AdminModule { }
