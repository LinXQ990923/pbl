import { Component, OnInit } from '@angular/core';
import {Projects} from "../../../share/projects";
import {CourseDetail} from "../../../share/course-detail";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../../service/course.service";
import {ProjectService} from "../../../service/project.service";
import {NzMessageService, NzModalRef, NzModalService} from "ng-zorro-antd";

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
  confirmModal?: NzModalRef;

  constructor(
    private router:ActivatedRoute,
    private course:CourseService,
    private project:ProjectService,
    private modal:NzModalService,
    private message: NzMessageService,
    private route:Router,
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

  notarize(course_id):void{
    this.confirmModal = this.modal.confirm({
      nzTitle: '确认删除吗？',
      nzContent: '点击确认后，该课程将被删除',
      nzOnOk: () =>
        new Promise((resolve, reject) => {
          setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
        }).catch(() => console.log('Oops errors!'))
    });
  }

  deleteCourse(course_id):void{
    this.confirmModal = this.modal.confirm({
      nzTitle: '确认删除吗？',
      nzContent: '点击确认后，该课程将被删除',
      nzOnOk: () =>{
        this.course.deleteCourse(course_id).subscribe(res=>{
          if(res.status === "true"){
            this.message.create("success","删除课程成功");
            this.route.navigate(['teacher','myCourses'])
          }
          else{
            this.modal.error({
              nzTitle: '删除课程失败',
              nzContent: res.message
            });
          }
        })
      }
    });
  }

  deleteProject(project_id):void{
    this.confirmModal = this.modal.confirm({
      nzTitle: '确认删除吗？',
      nzContent: '点击确认后，该课程将被删除',
      nzOnOk: () => {
        this.project.deleteProject(project_id).subscribe(res=>{
          if(res.status === "true"){
            this.message.create("success","删除项目成功");
            location.reload();
          }
          else{
            this.modal.error({
              nzTitle: '删除项目失败',
              nzContent: res.message
            });
          }
        })
      }
    });
  }
}
