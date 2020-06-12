import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import {
  NzAvatarModule, NzButtonModule,
  NzCommentModule,
  NzGridModule, NzInputModule,
  NzLayoutModule, NzListModule, NzMessageService,
  NzModalModule,
  NzModalService,
  NzPageHeaderModule,
  NzTypographyModule
} from "ng-zorro-antd";
import { PageErrorComponent } from './components/page-error/page-error.component';
import {MemoryService} from "./service/memory.service";
import {AuthService} from "./service/auth.service";
import {StudentService} from "./service/student.service";
import {CourseService} from "./service/course.service";
import { CommentsComponent } from './components/comments/comments.component';
import {ProjectService} from "./service/project.service";
import {TeacherService} from "./service/teacher.service";

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    PageErrorComponent,
    CommentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzLayoutModule,
    NzPageHeaderModule,
    NzGridModule,
    NzTypographyModule,
    NzModalModule,
    NzCommentModule,
    NzAvatarModule,
    NzListModule,
    NzInputModule,
    NzButtonModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN },
    NzModalService,
    MemoryService,
    NzMessageService,
    AuthService,
    StudentService,
    CourseService,
    ProjectService,
    TeacherService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
