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
  course_id:string;

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

  proportionValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value<40){
        observer.next({ error: true, less: true });
      }else if (control.value>100){
        observer.next({ error: true, more: true });
      } else observer.next(null);
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
      ]],
      teacherProportion:[100,[
        Validators.required
      ],[
        this.proportionValidator
      ]]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.course_id=params.get("course_id");
    });
  }

  submitForm():void{
    this.project.createProject({
      course_id:this.course_id,
      name:this.validateForm.value.name,
      description:this.validateForm.value.description,
      start_time:this.formatDate(this.today),
      end_time:this.formatDate(this.validateForm.value.date),
      teacherProportion:String(this.validateForm.value.teacherProportion),
      studentProportion:String(100-Number(this.validateForm.value.teacherProportion))
    }).subscribe(res=>{
      if (res.status=='true'){
        this.message.create("success", "增设成功");
        this.router.navigate(['/teacher/courseDetail',this.course_id]);
      }else {
        this.modal.error({
          nzTitle:"增设失败",
          nzContent:res.message
        })
      }
    });
  }
}
