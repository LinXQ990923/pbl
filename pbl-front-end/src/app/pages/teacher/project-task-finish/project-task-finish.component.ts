import { Component, OnInit } from '@angular/core';
import {TaskFinish} from "../../../share/task-finish";
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../service/project.service";

@Component({
  selector: 'app-project-task-finish',
  templateUrl: './project-task-finish.component.html',
  styleUrls: ['./project-task-finish.component.css']
})
export class ProjectTaskFinishComponent implements OnInit {
  project_id:string;
  taskFinish:TaskFinish[];
  panels = [];

  constructor(
    private route:ActivatedRoute,
    private project:ProjectService,

  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getTaskFinish(this.project_id).subscribe(res=>{
        this.taskFinish=res;
        for (let item of res){
          this.panels.push({
            active: false,
            disabled: false,
            name: item.name
          });
        }
      });
    });
  }
}
