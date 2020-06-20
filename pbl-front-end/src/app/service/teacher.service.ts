import { Injectable } from '@angular/core';
/* 引入包 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
/* 可能用到的操作符 */
import {catchError,map,tap} from 'rxjs/operators';
/* 回调对象 */
import {Observable,of, ObservableInput} from 'rxjs';
import {Result} from "../share/result";
import {Teacher} from "../share/teacher";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  getInfoUrl="/teacher/getinfo";
  loginoutUrl="/teacher/loginout";
  updateInfoUrl="/teacher/info/update";
  updatePasswordUrl="/teacher/password/update";

  constructor(
    private http:HttpClient/* 依赖注入 */
  ) { }

  getTeacherInfo():Observable<Teacher>{
    let url = this.getInfoUrl;
    return this.http.get<Teacher>(url).pipe();

    // let url = "/assets/data/teacherInfo.json";
    // return this.http.get<Teacher>(url).pipe();
  }

  loginout():Observable<Result>{
    let url = this.loginoutUrl;
    return this.http.get<Result>(url).pipe();

    // let url = "/assets/data/success.json";
    // return this.http.get<Result>(url).pipe();
  }

  updateInfo(data):Observable<Result>{
    let url = this.updateInfoUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();

    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.post<Result>(url,data,httpOptions).pipe();
  }

  updatePassword(data):Observable<Result>{
    let url = this.updatePasswordUrl;
    return this.http.post<Result>(url,data,httpOptions).pipe();
    //
    // console.log(data);
    // let url = "/assets/data/success.json";
    // return this.http.post<Result>(url,data,httpOptions).pipe();
  }
}
