import { Component, OnInit } from '@angular/core';
import {StudentService} from "../../../service/student.service";
import {Student} from "../../../share/student";
import {NzModalService} from "ng-zorro-antd";
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-main',
  templateUrl: './student-main.component.html',
  styleUrls: ['./student-main.component.css']
})
export class StudentMainComponent implements OnInit {
  isCollapsed = false;
  studentInfo: Student;

  constructor(
    private student: StudentService,
    private modal: NzModalService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.student.getStudentInfo().subscribe(result=>{
      this.studentInfo = result;
    })
  }

  logout(){
    this.student.loginout().subscribe(result=>{
      if (result.status=="true"){
        this.router.navigate(['/','auth','student','login']);
      }
      else{
        this.modal.error({
          nzTitle: '退出登录失败',
          nzContent: result.message
        });
      }
    })
  }

}
