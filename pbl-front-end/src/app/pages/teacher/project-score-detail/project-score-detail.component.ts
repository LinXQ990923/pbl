import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../../service/project.service";
import {ActivatedRoute} from "@angular/router";
import {Score} from "../../../share/score";

@Component({
  selector: 'app-project-score-detail',
  templateUrl: './project-score-detail.component.html',
  styleUrls: ['./project-score-detail.component.css']
})
export class ProjectScoreDetailComponent implements OnInit {
  student_id:string;
  project_id:string;
  scores:Score[];

  constructor(
    private project:ProjectService,
    private route:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.student_id=this.route.snapshot.queryParams["student_id"];
    this.project_id=this.route.snapshot.queryParams["project_id"];
    this.project.getStudentScore({
      project_id:this.project_id,
      student_id:this.student_id
    }).subscribe(res=>{
      this.scores=res;
    })
  }

}
