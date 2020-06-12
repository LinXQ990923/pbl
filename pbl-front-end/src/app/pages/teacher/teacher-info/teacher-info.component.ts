import { Component, OnInit } from '@angular/core';
import {Teacher} from "../../../share/teacher";
import {TeacherService} from "../../../service/teacher.service";

@Component({
  selector: 'app-teacher-info',
  templateUrl: './teacher-info.component.html',
  styleUrls: ['./teacher-info.component.css']
})
export class TeacherInfoComponent implements OnInit {
  teacherInfo:Teacher;

  constructor(
    private teacher:TeacherService
  ) { }

  ngOnInit(): void {
    this.teacher.getTeacherInfo().subscribe(result=>{
      this.teacherInfo=result;
    })
  }

}
