import { Component, OnInit } from '@angular/core';
import {Student} from "../../../share/student";
import {CourseDetail} from "../../../share/course-detail";
import {AuthService} from "../../../service/auth.service";
import {CourseService} from "../../../service/course.service";
import {NzModalService} from "ng-zorro-antd";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  listOfData:CourseDetail[];

  constructor(
    private auth:AuthService,
    private course:CourseService,
    private modal:NzModalService
  ) { }

  ngOnInit(): void {
    this.auth.getAllCourse().subscribe(res=>{
      this.listOfData=res;
    })
  }

  deleteCourse(id):void{
    this.course.deleteCourse(id).subscribe(result=>{
      if(result.status === "true"){
        location.reload();
      }
      else{
        this.modal.error({
          nzTitle: '删除失败',
          nzContent: result.message
        });
      }
    })
  }

}
