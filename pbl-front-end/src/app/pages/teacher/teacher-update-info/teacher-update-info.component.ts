import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Teacher} from "../../../share/teacher";
import {Observable, Observer} from "rxjs";
import {NzMessageService, NzModalService, UploadFile} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {TeacherService} from "../../../service/teacher.service";

@Component({
  selector: 'app-teacher-update-info',
  templateUrl: './teacher-update-info.component.html',
  styleUrls: ['./teacher-update-info.component.css']
})
export class TeacherUpdateInfoComponent implements OnInit {
  validateForm: FormGroup;
  loading = false;
  avatarUrl?: string;
  respUrl: string;
  teacherInfo:Teacher;

  usernameValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<2) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>6){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  schoolValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value){
      return {};
    } else if (control.value.length<4) {
      return { error: true, minLength: true };
    } else if (control.value.length>20) {
      return { maxLength: true, error: true };
    }
    return {};
  };

  departmentValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value){
      return {};
    } else if (control.value.length<3) {
      return { error: true, minLength: true };
    } else if (control.value.length>20) {
      return { maxLength: true, error: true };
    }
    return {};
  };

  constructor(
    private fb: FormBuilder,
    private msg: NzMessageService,
    private modal: NzModalService,
    private router: Router,
    private teacher:TeacherService,
  ) {
    this.teacher.getTeacherInfo().subscribe(res=>{
      this.teacherInfo=res;
      this.validateForm = this.fb.group({
        username:[null, [
          Validators.required,
        ],
          [
            this.usernameValidator
          ]],
        school: [null, [
          this.schoolValidator
        ]],
        department: [null, [
          this.departmentValidator
        ]],
        email: ['', [
          Validators.email,
          Validators.required
        ]],
      });
      this.initForm();
    })
  }

  initForm():void{
    this.validateForm.setValue({
      username: this.teacherInfo.name,
      school: this.teacherInfo.school,
      department: this.teacherInfo.department,
      email: this.teacherInfo.email
    })
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.initForm();
  }

  ngOnInit(): void {
  }

  beforeUpload = (file: UploadFile) => {
    return new Observable((observer: Observer<boolean>) => {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.msg.error('You can only upload JPG file!');
        observer.complete();
        return;
      }
      const isLt2M = file.size! / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.msg.error('Image must smaller than 2MB!');
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
        this.msg.error('Network error');
        this.loading = false;
        break;
    }
  }

  submitForm(): void {
    if (!this.respUrl){
      this.modal.error({
        nzTitle: '表单错误',
        nzContent: '请选择上传的头像图片',
      });
    }
    console.log(this.validateForm.value);
    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }
    if (this.validateForm.valid){
      this.teacher.updateInfo({
        name: this.validateForm.value.username,
        school: this.validateForm.value.school,
        department: this.validateForm.value.department,
        email: this.validateForm.value.email,
        image: this.respUrl
      }).subscribe(result=>{
        if(result.status=="true"){
          this.modal.success({
            nzTitle: '成功',
            nzContent: '修改信息成功',
            nzOnOk: () =>{
              this.router.navigate(['/','teacher','info']);
            }
          });
        }
        else{
          this.modal.error({
            nzTitle: '失败',
            nzContent: '修改失败，请稍后重试',
          });
        }
      });
    }
  }

}
