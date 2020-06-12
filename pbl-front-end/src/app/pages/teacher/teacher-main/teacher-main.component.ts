import { Component, OnInit } from '@angular/core';
import {Teacher} from "../../../share/teacher";
import {TeacherService} from "../../../service/teacher.service";
import {NzModalService} from "ng-zorro-antd";
import {Router} from "@angular/router";

@Component({
  selector: 'app-teacher-main',
  templateUrl: './teacher-main.component.html',
  styleUrls: ['./teacher-main.component.css']
})
export class TeacherMainComponent implements OnInit {
  isCollapsed = false;
  teacherInfo:Teacher;

  constructor(
    private teacher:TeacherService,
    private modal:NzModalService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.teacher.getTeacherInfo().subscribe(result=>{
      this.teacherInfo=result;
    })
  }

  logout(){
    this.teacher.loginout().subscribe(result=>{
      if (result.status=="true"){
        this.router.navigate(['/','auth','teacher','login']);
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
