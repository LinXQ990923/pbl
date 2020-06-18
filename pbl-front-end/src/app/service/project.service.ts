import { Injectable } from '@angular/core';
/* 引入包 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
/* 可能用到的操作符 */
import {catchError,map,tap} from 'rxjs/operators';
/* 回调对象 */
import {Observable,of, ObservableInput} from 'rxjs';
import {Course} from "../share/course";
import {CourseDetail} from "../share/course-detail";
import {Projects} from "../share/projects";
import {Result} from "../share/result";
import {Member} from "../share/member";
import {Score} from "../share/score";
import {Comment} from "../share/comment";
import {File} from "../share/file";
import {TaskFinish} from "../share/task-finish";
import {Task} from "../share/task";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  getProjectListUrl="/course/projects";
  addProjectUrl="/course/project/add";
  getProjectUrl="/course/project/Detail";
  getMembersUrl="/course/project/members";
  getScoreUrl="/course/project/score";
  getCommentsUrl="/course/project/comment";
  addCommentUrl="/course/project/comment/add";
  getFilesUrl="/course/project/file";
  createTaskUrl="/course/project/task/create";
  getTaskFinishUrl="/course/project/task/finished";
  getScoreCommentsUrl="/course/project/score/comment";//返回被评分人的讨论记录
  getScoreTaskUrl="/course/project/score/task";//返回被评分人的任务完成情况
  addScoreUrl="/course/project/score/add";
  getOwnTaskUrl="/course/project/task";//返回自己的任务完成情况
  finishTaskUrl="/course/project/task/finish";//完成任务
  deleteProjectUrl="/course/project/delete";
  createProjectUrl="/course/project/create";
  getStudentCommentsUrl="/course/project/student/comment";//返回指定学生的讨论记录
  getStudentTaskUrl="/course/project/student/task";//返回指定学生的任务完成情况
  getStudentScoreUrl="/course/project/student/score";

  constructor(
    private http:HttpClient/* 依赖注入 */
  ) { }

  getProjectList(data):Observable<Projects[]>{
    // let url = this.getProjectListUrl+"?course_id="+data;
    // return this.http.get<Projects[]>(url).pipe();

    let url = "/assets/data/projects.json";
    return this.http.get<Projects[]>(url).pipe();
  }

  addCourse(data):Observable<Result>{
    // let url = this.addProjectUrl+"?project_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  getProjectDetail(data):Observable<Projects>{
    // let url = this.getProjectUrl+"?project_id="+data;
    // return this.http.get<Projects>(url).pipe();

    let url = "/assets/data/project.json";
    return this.http.get<Projects>(url).pipe();
  }

  getProjectMembers(data):Observable<Member[]>{
    // let url = this.getMembersUrl+"?project_id="+data;
    // return this.http.get<Member[]>(url).pipe();

    let url = "/assets/data/members.json";
    return this.http.get<Member[]>(url).pipe();
  }

  getScore(data):Observable<Score[]>{
    // let url = this.getScoreUrl+"?project_id="+data;
    // return this.http.get<Score[]>(url).pipe();

    let url = "/assets/data/score.json";
    return this.http.get<Score[]>(url).pipe();
  }

  getComments(data):Observable<Comment[]>{
    // let url = this.getCommentsUrl+"?project_id="+data;
    // return this.http.get<Comment[]>(url).pipe();

    let url = "/assets/data/comments.json";
    return this.http.get<Comment[]>(url).pipe();
  }

  getFiles(data):Observable<File[]>{
    // let url = this.getFilesUrl+"?project_id="+data;
    // return this.http.get<File[]>(url).pipe();

    let url = "/assets/data/files.json";
    return this.http.get<File[]>(url).pipe();
  }

  addComment(data):Observable<Result>{
    // let url = this.addCommentUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe();
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  createTask(data):Observable<Result>{
    // let url = this.createTaskUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe();
    console.log(data);
    let url = "/assets/data/error.json";
    return this.http.get<Result>(url).pipe();
  }

  getTaskFinish(data):Observable<TaskFinish[]>{
    // let url = this.getTaskFinishUrl+"?project_id="+data;
    // return this.http.get<TaskFinish[]>(url).pipe();

    let url = "/assets/data/taskFinish.json";
    return this.http.get<TaskFinish[]>(url).pipe();
  }

  getScoreComments(data):Observable<Comment[]>{
    // let url = this.getScoreCommentsUrl+"?project_id="+data;
    // return this.http.get<Comment[]>(url).pipe();

    let url = "/assets/data/comments.json";
    return this.http.get<Comment[]>(url).pipe();
  }

  getScoreTask(data):Observable<Task[]>{
    // let url = this.getScoreTaskUrl+"?project_id="+data;
    // return this.http.get<Task[]>(url).pipe();

    let url = "/assets/data/tasks.json";
    return this.http.get<Task[]>(url).pipe();
  }

  addScore(data):Observable<Result>{
    // let url = this.addScoreUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe();
    console.log(data);
    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  getOwnTask(data):Observable<Task[]>{
    // let url = this.getOwnTaskUrl+"?project_id="+data;
    // return this.http.get<Task[]>(url).pipe();

    let url = "/assets/data/tasks.json";
    return this.http.get<Task[]>(url).pipe();
  }

  finishTask(data):Observable<Result>{
    // let url = this.finishTaskUrl+"?task_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  deleteProject(data):Observable<Result>{
    // let url = this.deleteProjectUrl+"?project_id="+data;
    // return this.http.get<Result>(url).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  createProject(data):Observable<Result>{
    // let url = this.createProjectUrl;
    // return this.http.post<Result>(url,data,httpOptions).pipe();

    let url = "/assets/data/success.json";
    return this.http.get<Result>(url).pipe();
  }

  getStudentComments(data):Observable<Comment[]>{
    // let url = this.getStudentCommentsUrl;
    // return this.http.post<Comment[]>(url,data,httpOptions).pipe();

    let url = "/assets/data/comments.json";
    return this.http.get<Comment[]>(url).pipe();
  }

  getStudentTask(data):Observable<Task[]>{
    // let url = this.getStudentTaskUrl;
    // return this.http.post<Task[]>(url,data,httpOptions).pipe();

    let url = "/assets/data/tasks.json";
    return this.http.get<Task[]>(url).pipe();
  }

  getStudentScore(data):Observable<Score[]>{
    // let url = this.getStudentScoreUrl;
    // return this.http.post<Score[]>(url,data,httpOptions).pipe();

    let url = "/assets/data/score.json";
    return this.http.get<Score[]>(url).pipe();
  }
}
