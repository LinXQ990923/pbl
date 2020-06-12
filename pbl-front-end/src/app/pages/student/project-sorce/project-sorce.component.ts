import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../../service/project.service";
import {Score} from "../../../share/score";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-project-sorce',
  templateUrl: './project-sorce.component.html',
  styleUrls: ['./project-sorce.component.css']
})
export class ProjectSorceComponent implements OnInit {
  project_id:string;
  scores:Score[];

  constructor(
    private project:ProjectService,
    private route:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getScore(this.project_id).subscribe(res=>{
        this.scores=res;
      });
    });
  }

}
