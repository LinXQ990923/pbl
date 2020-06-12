import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentMainComponent } from './student-main/student-main.component';
import {
  NzAvatarModule, NzBadgeModule,
  NzBreadCrumbModule, NzButtonModule, NzCardModule, NzCollapseModule, NzCommentModule, NzDatePickerModule,
  NzDescriptionsModule, NzDividerModule, NzFormModule,
  NzIconModule, NzInputModule,
  NzLayoutModule, NzListModule,
  NzMenuModule, NzMessageService, NzPaginationModule, NzSkeletonModule, NzTagModule, NzTypographyModule, NzUploadModule
} from "ng-zorro-antd";
import { StudentInfoComponent } from './student-info/student-info.component';
import { StudentUpdateInfoComponent } from './student-update-info/student-update-info.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { StudentUpdatePasswordComponent } from './student-update-password/student-update-password.component';
import { StudentAllCourseComponent } from './student-all-course/student-all-course.component';
import { StudentMyCourseComponent } from './student-my-course/student-my-course.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { ProjectSorceComponent } from './project-sorce/project-sorce.component';
import { ProjectCommentAreaComponent } from './project-comment-area/project-comment-area.component';
import { ProjectFileAreaComponent } from './project-file-area/project-file-area.component';
import { ProjectCreateTaskComponent } from './project-create-task/project-create-task.component';
import { ProjectTaskFinishComponent } from './project-task-finish/project-task-finish.component';
import { ProjectScoreCreateComponent } from './project-score-create/project-score-create.component';
import { ProjectTaskListComponent } from './project-task-list/project-task-list.component';


@NgModule({
  declarations: [StudentMainComponent, StudentInfoComponent, StudentUpdateInfoComponent, StudentUpdatePasswordComponent, StudentAllCourseComponent, StudentMyCourseComponent, CourseDetailComponent, ProjectDetailComponent, ProjectSorceComponent, ProjectCommentAreaComponent, ProjectFileAreaComponent, ProjectCreateTaskComponent, ProjectTaskFinishComponent, ProjectScoreCreateComponent, ProjectTaskListComponent ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    NzLayoutModule,
    NzMenuModule,
    NzIconModule,
    NzBreadCrumbModule,
    NzAvatarModule,
    NzDescriptionsModule,
    NzBadgeModule,
    NzFormModule,
    ReactiveFormsModule,
    NzInputModule,
    NzButtonModule,
    NzUploadModule,
    NzCardModule,
    NzPaginationModule,
    NzDividerModule,
    NzListModule,
    NzSkeletonModule,
    NzTypographyModule,
    NzCommentModule,
    FormsModule,
    NzDatePickerModule,
    NzCollapseModule,
    NzTagModule,
  ]
})
export class StudentModule { }
