import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import differenceInCalendarDays from "date-fns/differenceInCalendarDays";
import {ActivatedRoute, Router} from "@angular/router";
import {NzMessageService, NzModalService, UploadFile} from "ng-zorro-antd";
import {CourseService} from "../../../service/course.service";
import {Teacher} from "../../../share/teacher";
import {AuthService} from "../../../service/auth.service";

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.css']
})
export class CourseCreateComponent implements OnInit {
  validateForm: FormGroup;
  today = new Date();
  loading = false;
  avatarUrl?: string;
  respUrl: string;
  teachers:Teacher[];

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
    private course:CourseService,
    private auth:AuthService
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
      teacher:[null,[
        Validators.required
      ]]
    });
  }

  ngOnInit(): void {
    this.auth.getAllTeacher().subscribe(res=>{
      this.teachers=res;
    })
  }

  submitForm():void{
    if (!this.respUrl){
      this.modal.error({
        nzTitle: '表单错误',
        nzContent: '请选择上传的课程封面图片',
      });
      return;
    }
    this.auth.createCourse({
      teacher_id:this.validateForm.value.teacher.id,
      name:this.validateForm.value.name,
      description:this.validateForm.value.description,
      start_time:this.formatDate(this.today),
      end_time:this.formatDate(this.validateForm.value.date),
      image:this.respUrl
    }).subscribe(res=>{
      if (res.status=='true'){
        this.message.create("success", "增设成功");
        this.router.navigate(['admin','courses']);
      }else {
        this.modal.error({
          nzTitle:"增设失败",
          nzContent:res.message
        })
      }
    });
  }

  beforeUpload = (file: UploadFile) => {
    return new Observable((observer: Observer<boolean>) => {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.message.error('You can only upload JPG file!');
        observer.complete();
        return;
      }
      const isLt2M = file.size! / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.message.error('Image must smaller than 2MB!');
        observer.complete();
        return;
      }
      observer.next(isJpgOrPng && isLt2M);
      observer.complete();
    });
  };

  private getBase64(img: File, callback: (img: string) => void): void {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result!.toString()));
    reader.readAsDataURL(img);
  }

  handleChange(info: { file: UploadFile }): void {
    switch (info.file.status) {
      case 'uploading':
        this.loading = true;
        break;
      case 'done':
        // Get this url from response in real world.
        this.respUrl = info.file.response.path;
        this.getBase64(info.file!.originFileObj!, (img: string) => {
          this.loading = false;
          this.avatarUrl = img;
        });
        break;
      case 'error':
        this.message.error('Network error');
        this.loading = false;
        break;
    }
  }
}
