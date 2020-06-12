import { Component, OnInit } from '@angular/core';
import {Projects} from "../../../share/projects";
import {Member} from "../../../share/member";
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../service/project.service";

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {
  project_id:string;
  projectDetail:Projects;
  memberList:Member[];

  constructor(
    private router:ActivatedRoute,
    private project:ProjectService,
  ) { }

  ngOnInit(): void {
    this.router.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getProjectDetail(this.project_id).subscribe(res=>{
        this.projectDetail=res;
        this.project.getProjectMembers(this.project_id).subscribe(result=>{
          this.memberList=result;
        });
      });
    });
  }

}
