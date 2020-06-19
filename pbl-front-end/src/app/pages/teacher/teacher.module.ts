import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeacherRoutingModule } from './teacher-routing.module';
import { TeacherMainComponent } from './teacher-main/teacher-main.component';
import {
    NzAvatarModule, NzBadgeModule,
    NzBreadCrumbModule, NzButtonModule, NzCardModule, NzCollapseModule, NzCommentModule, NzDatePickerModule,
    NzDescriptionsModule, NzDividerModule, NzFormModule,
    NzIconModule, NzInputModule, NzInputNumberModule,
    NzLayoutModule, NzListModule,
    NzMenuModule, NzPaginationModule, NzRadioModule, NzTagModule, NzTypographyModule, NzUploadModule
} from "ng-zorro-antd";
import { TeacherInfoComponent } from './teacher-info/teacher-info.component';
import { TeacherUpdateInfoComponent } from './teacher-update-info/teacher-update-info.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TeacherUpdatePasswordComponent } from './teacher-update-password/teacher-update-password.component';
import { TeacherMyCourseComponent } from './teacher-my-course/teacher-my-course.component';
import { TeacherCourseCreateComponent } from './teacher-course-create/teacher-course-create.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { TeacherProjectCreateComponent } from './teacher-project-create/teacher-project-create.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { ProjectCommentAreaComponent } from './project-comment-area/project-comment-area.component';
import { ProjectFileAreaComponent } from './project-file-area/project-file-area.component';
import { ProjectTaskCreateComponent } from './project-task-create/project-task-create.component';
import { ProjectTaskFinishComponent } from './project-task-finish/project-task-finish.component';
import { ProjectScoreCreateComponent } from './project-score-create/project-score-create.component';
import { ProjectScoreDetailComponent } from './project-score-detail/project-score-detail.component';


@NgModule({
  declarations: [TeacherMainComponent, TeacherInfoComponent, TeacherUpdateInfoComponent, TeacherUpdatePasswordComponent, TeacherMyCourseComponent, TeacherCourseCreateComponent, CourseDetailComponent, TeacherProjectCreateComponent, ProjectDetailComponent, ProjectCommentAreaComponent, ProjectFileAreaComponent, ProjectTaskCreateComponent, ProjectTaskFinishComponent, ProjectScoreCreateComponent, ProjectScoreDetailComponent],
    imports: [
        CommonModule,
        TeacherRoutingModule,
        NzLayoutModule,
        NzAvatarModule,
        NzMenuModule,
        NzIconModule,
        NzBreadCrumbModule,
        NzDescriptionsModule,
        NzBadgeModule,
        NzFormModule,
        NzInputModule,
        FormsModule,
        ReactiveFormsModule,
        NzUploadModule,
        NzButtonModule,
        NzCardModule,
        NzPaginationModule,
        NzDatePickerModule,
        NzListModule,
        NzRadioModule,
        NzDividerModule,
        NzTypographyModule,
        NzCommentModule,
        NzCollapseModule,
        NzTagModule,
        NzInputNumberModule
    ]
})
export class TeacherModule { }
