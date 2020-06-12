import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminMainComponent} from "./admin-main/admin-main.component";
import {CourseCreateComponent} from "./course-create/course-create.component";
import {AdminStudentsComponent} from "./admin-students/admin-students.component";
import {AdminTeachersComponent} from "./admin-teachers/admin-teachers.component";
import {CourseListComponent} from "./course-list/course-list.component";


const routes: Routes = [
  {path:'',component:AdminMainComponent,children:[
      {path:'students',component:AdminStudentsComponent},
      {path:'teachers',component:AdminTeachersComponent},
      {path:'courses',component:CourseListComponent},
      {path:'courses/create',component:CourseCreateComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
