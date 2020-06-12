import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../../service/project.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import differenceInCalendarDays from "date-fns/differenceInCalendarDays";

@Component({
  selector: 'app-project-task-create',
  templateUrl: './project-task-create.component.html',
  styleUrls: ['./project-task-create.component.css']
})
export class ProjectTaskCreateComponent implements OnInit {
  project_id:string;
  validateForm: FormGroup;
  today = new Date();

  formatDate = ( time: any ) => {
    // 格式化日期，获取今天的日期
    const Dates = new Date( time );
    const year: number = Dates.getFullYear();
    const month: any = ( Dates.getMonth() + 1 ) < 10 ? '0' + ( Dates.getMonth() + 1 ) : ( Dates.getMonth() + 1 );
    const day: any = Dates.getDate() < 10 ? '0' + Dates.getDate() : Dates.getDate();
    return year + '-' + month + '-' + day;
  };

  nameValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length>20){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  constructor(
    private route:ActivatedRoute,
    private fb: FormBuilder,
    private project:ProjectService,
    private modal:NzModalService,
    private router:Router,
    private message:NzMessageService
  ) {
    this.validateForm = this.fb.group({
      date: [null, [
        Validators.required,
      ]],
      name: [null, [
        Validators.required,
      ],[
        this.nameValidator
      ]],
      description: [null, [
        Validators.required,
      ]],
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
    });
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) < 0;
  };

  submitForm():void{
    this.project.createTask({
      name:this.validateForm.value.name,
      description:this.validateForm.value.description,
      start_time:this.formatDate(this.today),
      end_time:this.formatDate(this.validateForm.value.date)
    }).subscribe(res=>{
      if (res.status=='true'){
        this.message.create("success", "发布成功");
        this.router.navigate(['student','projectDetail',this.project_id]);
      }else {
        this.modal.error({
          nzTitle:"发布失败",
          nzContent:res.message
        })
      }
    });
  }
}
