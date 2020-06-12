import { Component, OnInit } from '@angular/core';
import {StudentService} from "../../../service/student.service";
import {Student} from "../../../share/student";

@Component({
  selector: 'app-student-info',
  templateUrl: './student-info.component.html',
  styleUrls: ['./student-info.component.css']
})
export class StudentInfoComponent implements OnInit {
  studentInfo: Student;

  constructor(
    private student: StudentService,
  ) { }

  ngOnInit(): void {
    this.student.getStudentInfo().subscribe(result=>{
      this.studentInfo=result;
    })
  }

}
