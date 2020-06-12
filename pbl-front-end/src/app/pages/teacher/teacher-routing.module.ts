import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TeacherMainComponent} from "./teacher-main/teacher-main.component";
import {TeacherInfoComponent} from "./teacher-info/teacher-info.component";
import {TeacherUpdateInfoComponent} from "./teacher-update-info/teacher-update-info.component";
import {TeacherUpdatePasswordComponent} from "./teacher-update-password/teacher-update-password.component";
import {TeacherMyCourseComponent} from "./teacher-my-course/teacher-my-course.component";
import {TeacherCourseCreateComponent} from "./teacher-course-create/teacher-course-create.component";
import {CourseDetailComponent} from "./course-detail/course-detail.component";
import {TeacherProjectCreateComponent} from "./teacher-project-create/teacher-project-create.component";
import {ProjectDetailComponent} from "./project-detail/project-detail.component";
import {ProjectCommentAreaComponent} from "./project-comment-area/project-comment-area.component";
import {ProjectFileAreaComponent} from "./project-file-area/project-file-area.component";
import {ProjectTaskCreateComponent} from "./project-task-create/project-task-create.component";
import {ProjectTaskFinishComponent} from "./project-task-finish/project-task-finish.component";
import {ProjectScoreCreateComponent} from "./project-score-create/project-score-create.component";
import {ProjectScoreDetailComponent} from "./project-score-detail/project-score-detail.component";

const routes: Routes = [
  {path:'',component:TeacherMainComponent,children:[
      {path:'info',component:TeacherInfoComponent},
      {path:'info/update', component:TeacherUpdateInfoComponent},
      {path:'password/update', component:TeacherUpdatePasswordComponent},
      {path:'myCourses',component:TeacherMyCourseComponent},
      {path:'course/create',component:TeacherCourseCreateComponent},
      {path:'courseDetail/:course_id',component:CourseDetailComponent},
      {path:'project/create',component:TeacherProjectCreateComponent},
      {path:'projectDetail/:project_id', component:ProjectDetailComponent},
      {path:'projectComment/:project_id', component:ProjectCommentAreaComponent},
      {path:'projectFile/:project_id', component:ProjectFileAreaComponent},
      {path:'projectTask/create/:project_id', component:ProjectTaskCreateComponent},
      {path:'projectTask/finish/:project_id', component:ProjectTaskFinishComponent},
      {path:'projectScore/create',component:ProjectScoreCreateComponent},
      {path:'projectScore',component:ProjectScoreDetailComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
