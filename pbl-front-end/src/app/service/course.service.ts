import { Injectable } from '@angular/core';
/* 引入包 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
/* 可能用到的操作符 */
import {catchError,map,tap} from 'rxjs/operators';
/* 回调对象 */
import {Observable,of, ObservableInput} from 'rxjs';
import {Course} from "../share/course";
import {CourseDetail} from "../share/course-detail";
import {Result} from "../share/result";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  getAllCoursesUrl="/student/courses";
  getMyCoursesUrl="/student/mycourses";
  getCourseDetailUrl="/course/detail";
  addCourseUrl="/course/add";
  createCourseUrl="/course/create";
  deleteCourseUrl="course/delete";

  constructor(
    private http:HttpClient/* 依赖注入 */
  ) { }

  getAllCourses():Observable<Course[]>{
    // let url = this.getAllCoursesUrl;
    // return this.http.get<Course[]>(url).pipe();

    let url = "/assets/data/courses.json";
    return this.http.get<Course[]>(url).pipe();
  }

  getMyCourses():Observable<Course[]>{
    // let url = this.getMyCoursesUrl;
    // return this.http.get<Course[]>(url).pipe();

    let url = "/assets/data/courses.json";
    return this.http.get<Course[]>(url).pipe();
  }

  getCourseDetail(data):Observable<CourseDetail>{
    // let url = this.getCourseDetailUrl+"?course_id="+data;
    // return this.http.get<CourseDetail>(url).pipe();

    let url = "/assets/data/courseDetail.json";
    return this.http.get<CourseDetail>(url).pipe();
  }

  addCourse(data):Observable<Result>{
    // let url = this.addCourseUrl+"?course_id="+data;
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

  deleteCourse(data):Observable<Result>{
    // let url = this.deleteCourseUrl+"?course_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }
}
