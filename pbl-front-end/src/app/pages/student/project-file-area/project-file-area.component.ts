import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../service/project.service";
import {File} from "../../../share/file";
import {NzMessageService, UploadChangeParam} from "ng-zorro-antd";

@Component({
  selector: 'app-project-file-area',
  templateUrl: './project-file-area.component.html',
  styleUrls: ['./project-file-area.component.css']
})
export class ProjectFileAreaComponent implements OnInit {
  project_id:string;
  fileList:File[];

  constructor(
    private route:ActivatedRoute,
    private project:ProjectService,
    private msg: NzMessageService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getFiles(this.project_id).subscribe(res=>{
        this.fileList=res;
      });
    })
  }

  handleChange({ file }: UploadChangeParam): void {
    const status = file.status;
    if (status !== 'uploading') {
      console.log(file);
    }
    if (status === 'done') {
      this.msg.success(`${file.name} file uploaded successfully.`);
      location.reload();
    } else if (status === 'error') {
      this.msg.error(`${file.name} file upload failed.`);
    }
  }

  download(path):void{
    this.project.downloadFile("/up/1a95aff82.jpg").subscribe(res=>{

    });
  }

  delete(id):void{
    this.project.deleteFile(id).subscribe(res=>{
      if (res.status=="true"){
        this.msg.create("success","删除成功");
        location.reload();
      }else {
        this.msg.create("error","删除失败");
      }
    })

  }
}
