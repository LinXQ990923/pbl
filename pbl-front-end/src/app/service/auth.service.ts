import { Injectable } from '@angular/core';
/* 引入包 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
/* 可能用到的操作符 */
import {catchError,map,tap} from 'rxjs/operators';
/* 回调对象 */
import {Observable,of, ObservableInput} from 'rxjs';
import {Result} from "../share/result";
import {Student} from "../share/student";
import {Teacher} from "../share/teacher";
import {CourseDetail} from "../share/course-detail";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  studentLoginUrl="/student/login";
  studentSignupUrl="/student/signup";
  teacherLoginUrl="/teacher/login";
  teacherSignupUrl="/teacher/signup";
  adminLoginUrl="/admin/login";
  getAllStudentUrl="/admin/students";
  deleteStudentUrl="/admin/student/delete";
  getAllTeacherUrl="/admin/teachers";
  deleteTeacherUrl="/admin/teacher/delete";
  createCourseUrl="/admin/course/create";
  getAllCoursesUrl="/admin/courses";

  constructor(
    private http:HttpClient/* 依赖注入 */
  ) { }

  loginAsStudent(data):Observable<Result>{
    let url = this.studentLoginUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe(
    //   catchError(this.handleError)
    // );
  }

  signUpAsStudent(data):Observable<Result>{
    let url = this.studentSignupUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe(
    //   catchError(this.handleError)
    // );
  }

  signUpAsTeacher(data):Observable<Result>{
    let url = this.teacherSignupUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe(
      catchError(this.handleError)
    );
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe(
    //   catchError(this.handleError)
    // );
  }

  loginAsTeacher(data):Observable<Result>{
     let url = this.teacherLoginUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe(
    //   catchError(this.handleError)
    // );
  }

  loginAsAdmin(data):Observable<Result>{
    let url = this.adminLoginUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe(
    //   catchError(this.handleError)
    // );
  }

  getAllStudent():Observable<Student[]>{
    let url = this.getAllStudentUrl;
    return this.http.get<Student[]>(url).pipe();

    // let url = "/assets/data/students.json";
    // return this.http.get<Student[]>(url).pipe();
  }

  getAllTeacher():Observable<Teacher[]>{
    let url = this.getAllTeacherUrl;
    return this.http.get<Teacher[]>(url).pipe();

    // let url = "/assets/data/students.json";
    // return this.http.get<Teacher[]>(url).pipe();
  }

  deleteStudent(data):Observable<Result>{
    // let url = this.deleteStudentUrl+"?student_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  deleteTeacher(data):Observable<Result>{
    // let url = this.deleteTeacherUrl+"?teacher_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  createCourse(data):Observable<Result>{
    // let url = this.createCourseUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  getAllCourse():Observable<CourseDetail[]>{
    // let url = this.getAllCoursesUrl;
    // return this.http.get<CourseDetail[]>(url).pipe();

    let url = "/assets/data/courseDetails.json";
    return this.http.get<CourseDetail[]>(url).pipe();
  }

  private handleError(error: HttpErrorResponse, info:Observable<Result>):ObservableInput<Result>{
    return null;
  }
}
