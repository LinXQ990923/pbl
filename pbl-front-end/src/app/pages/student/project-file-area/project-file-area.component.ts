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

  handleChange({ file, fileList }: UploadChangeParam): void {
    const status = file.status;
    if (status !== 'uploading') {
      console.log(file, fileList);
    }
    if (status === 'done') {
      this.msg.success(`${file.name} file uploaded successfully.`);
    } else if (status === 'error') {
      this.msg.error(`${file.name} file upload failed.`);
    }
  }

}
