import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StudentMainComponent} from "./student-main/student-main.component";
import {StudentInfoComponent} from "./student-info/student-info.component";
import {StudentUpdateInfoComponent} from "./student-update-info/student-update-info.component";
import {StudentUpdatePasswordComponent} from "./student-update-password/student-update-password.component";
import {StudentAllCourseComponent} from "./student-all-course/student-all-course.component";
import {StudentMyCourseComponent} from "./student-my-course/student-my-course.component";
import {CourseDetailComponent} from "./course-detail/course-detail.component";
import {ProjectDetailComponent} from "./project-detail/project-detail.component";
import {ProjectSorceComponent} from "./project-sorce/project-sorce.component";
import {ProjectCommentAreaComponent} from "./project-comment-area/project-comment-area.component";
import {ProjectFileAreaComponent} from "./project-file-area/project-file-area.component";
import {ProjectCreateTaskComponent} from "./project-create-task/project-create-task.component";
import {ProjectTaskFinishComponent} from "./project-task-finish/project-task-finish.component";
import {ProjectScoreCreateComponent} from "./project-score-create/project-score-create.component";
import {ProjectTaskListComponent} from "./project-task-list/project-task-list.component";

const routes: Routes = [
  {path:'', component:StudentMainComponent, children:[
      {path:'info', component:StudentInfoComponent},
      {path:'info/update', component:StudentUpdateInfoComponent},
      {path:'password/update', component:StudentUpdatePasswordComponent},
      {path:'courses', component:StudentAllCourseComponent},
      {path:'myCourses', component:StudentMyCourseComponent},
      {path:'courseDetail/:course_id', component:CourseDetailComponent},
      {path:'projectDetail/:project_id', component:ProjectDetailComponent},
      {path:'projectScore/:project_id', component:ProjectSorceComponent},
      {path:'projectComment/:project_id', component:ProjectCommentAreaComponent},
      {path:'projectFile/:project_id', component:ProjectFileAreaComponent},
      {path:'projectTask/create/:project_id', component:ProjectCreateTaskComponent},
      {path:'projectTask/finish/:project_id', component:ProjectTaskFinishComponent},
      {path:'projectScore/create/:project_id', component:ProjectScoreCreateComponent},
      {path:'projectTask/:project_id', component:ProjectTaskListComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
