import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../service/project.service";
import {Task} from "../../../share/task";
import {NzMessageService, NzModalService} from "ng-zorro-antd";

@Component({
  selector: 'app-project-task-list',
  templateUrl: './project-task-list.component.html',
  styleUrls: ['./project-task-list.component.css']
})
export class ProjectTaskListComponent implements OnInit {
  project_id:string;
  data:Task[];
  date:any;

  constructor(
    private route:ActivatedRoute,
    private project:ProjectService,
    private modal:NzModalService,
    private message:NzMessageService
  ) {
    const formatDate = ( time: any ) => {
      // 格式化日期，获取今天的日期
      const Dates = new Date( time );
      const year: number = Dates.getFullYear();
      const month: any = ( Dates.getMonth() + 1 ) < 10 ? '0' + ( Dates.getMonth() + 1 ) : ( Dates.getMonth() + 1 );
      const day: any = Dates.getDate() < 10 ? '0' + Dates.getDate() : Dates.getDate();
      return year + '-' + month + '-' + day;
    };
    this.date = formatDate( new Date().getTime() );
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getOwnTask(this.project_id).subscribe(result=>{
        this.data=result;
      })
    });
  }

  finishTask(id):void{
    this.project.finishTask(id).subscribe(res=>{
      if(res.status === "true"){
        this.message.create("success","完成任务");
        for (let item of this.data){
          if (item.id==id){
            item.finish='true';
          }
        }
      }
      else{
        this.modal.error({
          nzTitle: '提交任务失败',
          nzContent: res.message
        });
      }
    })
  }

}
