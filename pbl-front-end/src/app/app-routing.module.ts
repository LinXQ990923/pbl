import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PageErrorComponent} from './components/page-error/page-error.component';
import {CommentsComponent} from "./components/comments/comments.component";


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/auth/student/login'},
  { path: 'auth', loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule) },
  { path: 'student', loadChildren: () => import('./pages/student/student.module').then(m => m.StudentModule) },
  { path: 'teacher', loadChildren: () => import('./pages/teacher/teacher.module').then(m => m.TeacherModule) },
  { path: 'admin', loadChildren: () => import('./pages/admin/admin.module').then(m => m.AdminModule) },
  { path: '404', component: PageErrorComponent },
  { path: 'test', component: CommentsComponent },
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
