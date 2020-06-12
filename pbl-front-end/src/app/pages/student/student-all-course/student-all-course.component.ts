import { Component, OnInit } from '@angular/core';
import {CourseService} from "../../../service/course.service";
import {Course} from "../../../share/course";

@Component({
  selector: 'app-student-all-course',
  templateUrl: './student-all-course.component.html',
  styleUrls: ['./student-all-course.component.css']
})
export class StudentAllCourseComponent implements OnInit {
  pageIndex = 1;
  pageSize = 12;
  total: number;
  courses:Course[];
  showList:Course[];

  constructor(
    private course:CourseService,
  ) { }

  ngOnInit(): void {
    this.course.getAllCourses().subscribe(res=>{
      for (let r of res){
        if (r.introduction.length>20){
          r.introduction = r.introduction.slice(0,18)+'...';
        }
      }
      this.courses=res;
      this.total=res.length;
      this.showList = res.slice(0, Math.min(this.pageSize, this.total));
    })
  }

  changePage(): void{
    this.showList = this.courses.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
  }
}
