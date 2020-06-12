import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../../service/project.service";
import {Task} from "../../../share/task";
import {Comment} from "../../../share/comment";
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {NzMessageService, NzModalService} from "ng-zorro-antd";

@Component({
  selector: 'app-project-score-create',
  templateUrl: './project-score-create.component.html',
  styleUrls: ['./project-score-create.component.css']
})
export class ProjectScoreCreateComponent implements OnInit {
  project_id:string;
  taskList:Task[];
  commentList:Comment[];
  validateForm: FormGroup;

  constructor(
    private route:ActivatedRoute,
    private project:ProjectService,
    private fb: FormBuilder,
    private router:Router,
    private message:NzMessageService,
    private modal:NzModalService
  ) {
    this.validateForm = this.fb.group({
      score: [null, [
        Validators.required,
      ],[
        this.scoreValidator
      ]],
      description: [null, [
        Validators.required,
      ]],
    });
  }

  scoreValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value<0||control.value>100){
        observer.next({ error: true, required: true });
      }else observer.next(null);
      observer.complete();
    });

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getScoreComments(this.project_id).subscribe(result=>{
        this.commentList=result;
        this.project.getScoreTask(this.project_id).subscribe(res=>{
          this.taskList=res;
        });
      });
    });
  }

  submitForm():void{
    this.project.addScore({
      score:this.validateForm.value.score,
      description:this.validateForm.value.description,
    }).subscribe(res=>{
      if (res.status=='true'){
        this.message.create("success", "评分成功");
        this.router.navigate(['student','projectDetail',this.project_id]);
      }else {
        this.modal.error({
          nzTitle:"评分失败",
          nzContent:res.message
        })
      }
    });
  }
}
