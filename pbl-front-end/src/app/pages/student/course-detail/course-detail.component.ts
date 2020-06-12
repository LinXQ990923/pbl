import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {CourseService} from "../../../service/course.service";
import {CourseDetail} from "../../../share/course-detail";
import {Projects} from "../../../share/projects";
import {ProjectService} from "../../../service/project.service";

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  course_id:string;
  loading = false;
  data: Projects[];
  courseDetail:CourseDetail;
  date:any;

  constructor(
    private router:ActivatedRoute,
    private course:CourseService,
    private project:ProjectService,
    private modal:NzModalService,
    private message: NzMessageService,
  ) { }

  ngOnInit(): void {
    this.router.paramMap.subscribe(params=>{
      this.course_id=params.get('course_id');
      this.course.getCourseDetail(this.course_id).subscribe(res=>{
        this.courseDetail=res;
        const formatDate = ( time: any ) => {
          // 格式化日期，获取今天的日期
          const Dates = new Date( time );
          const year: number = Dates.getFullYear();
          const month: any = ( Dates.getMonth() + 1 ) < 10 ? '0' + ( Dates.getMonth() + 1 ) : ( Dates.getMonth() + 1 );
          const day: any = Dates.getDate() < 10 ? '0' + Dates.getDate() : Dates.getDate();
          return year + '-' + month + '-' + day;
        };
        this.date = formatDate( new Date().getTime() );
        this.project.getProjectList(this.course_id).subscribe(result=>{
          this.data=result;
        });
      });
    });
  }

  addCourse(course_id):void{
    this.course.addCourse(course_id).subscribe(res=>{
      if(res.status === "true"){
        this.message.create("success","加入课程成功");
        this.courseDetail.isAdd="true";
      }
      else{
        this.modal.error({
          nzTitle: '加入课程失败',
          nzContent: res.message
        });
      }
    });
  }

  addProject(project_id):void{
    this.project.addCourse(project_id).subscribe(res=>{
      if(res.status === "true"){
        this.message.create("success","加入项目成功");
        for (let item of this.data){
          if (item.id==project_id){
            item.isAdd="true";
          }
        }
      }
      else{
        this.modal.error({
          nzTitle: '加入课程失败',
          nzContent: res.message
        });
      }
    });
  }
}
