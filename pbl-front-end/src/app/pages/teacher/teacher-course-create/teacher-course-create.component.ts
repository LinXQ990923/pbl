import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {NzMessageService, NzModalService, UploadFile} from "ng-zorro-antd";
import {CourseService} from "../../../service/course.service";
import differenceInCalendarDays from "date-fns/differenceInCalendarDays";

@Component({
  selector: 'app-teacher-course-create',
  templateUrl: './teacher-course-create.component.html',
  styleUrls: ['./teacher-course-create.component.css']
})
export class TeacherCourseCreateComponent implements OnInit {
  project_id:string;
  validateForm: FormGroup;
  today = new Date();
  loading = false;
  avatarUrl?: string;
  respUrl: string;

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
    private course:CourseService
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
  }

  submitForm():void{
    if (!this.respUrl){
      this.modal.error({
        nzTitle: '表单错误',
        nzContent: '请选择上传的课程封面图片',
      });
      return;
    }
    this.course.createCourse({
      name:this.validateForm.value.name,
      description:this.validateForm.value.description,
      start_time:this.formatDate(this.today),
      end_time:this.formatDate(this.validateForm.value.date),
      image:this.respUrl
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
