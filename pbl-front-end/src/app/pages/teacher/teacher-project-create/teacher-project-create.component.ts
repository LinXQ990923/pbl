import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import differenceInCalendarDays from "date-fns/differenceInCalendarDays";
import {ActivatedRoute, Router} from "@angular/router";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {ProjectService} from "../../../service/project.service";

@Component({
  selector: 'app-teacher-project-create',
  templateUrl: './teacher-project-create.component.html',
  styleUrls: ['./teacher-project-create.component.css']
})
export class TeacherProjectCreateComponent implements OnInit {
  validateForm: FormGroup;
  today = new Date();
  loading = false;

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

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) < 0;
  };

  constructor(
    private route:ActivatedRoute,
    private fb: FormBuilder,
    private modal:NzModalService,
    private router:Router,
    private message:NzMessageService,
    private project:ProjectService
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
      radioValue:['true',[
        Validators.required
      ]]
    });
  }

  ngOnInit(): void {
  }

  submitForm():void{
    this.project.createProject({
      name:this.validateForm.value.name,
      description:this.validateForm.value.description,
      start_time:this.formatDate(this.today),
      end_time:this.formatDate(this.validateForm.value.date),
      score:this.validateForm.value.radioValue
    }).subscribe(res=>{
      if (res.status=='true'){
        this.message.create("success", "增设成功");
        this.router.navigate(['teacher','myCourses']);
      }else {
        this.modal.error({
          nzTitle:"增设失败",
          nzContent:res.message
        })
      }
    });
  }
}
