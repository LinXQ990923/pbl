import { Injectable } from '@angular/core';
/* 引入包 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
/* 可能用到的操作符 */
import {catchError,map,tap} from 'rxjs/operators';
/* 回调对象 */
import {Observable,of, ObservableInput} from 'rxjs';
import {Result} from "../share/result";

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
  adminLoginUrl="/admin/login"

  constructor(
    private http:HttpClient/* 依赖注入 */
  ) { }

  loginAsStudent(data):Observable<Result>{
    // let url = this.studentLoginUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe(
    //   catchError(AuthService.handleError)
    // );
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  signUpAsStudent(data):Observable<Result>{
    // let url = this.studentSignupUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  loginAsTeacher(data):Observable<Result>{
     // let url = this.teacherLoginUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe(
    //   catchError(AuthService.handleError)
    // );
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  loginAsAdmin(data):Observable<Result>{
    // let url = this.adminLoginUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe(
    //   catchError(AuthService.handleError)
    // );
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse, info:Observable<Result>):ObservableInput<Result>{
    return null;
  }
}
