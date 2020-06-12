import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {NzMessageService, NzModalService, UploadFile} from "ng-zorro-antd";
import {StudentService} from "../../../service/student.service";
import {Router} from "@angular/router";
import {Student} from "../../../share/student";

@Component({
  selector: 'app-student-update-info',
  templateUrl: './student-update-info.component.html',
  styleUrls: ['./student-update-info.component.css']
})
export class StudentUpdateInfoComponent implements OnInit {
  validateForm: FormGroup;
  loading = false;
  avatarUrl?: string;
  respUrl: string;
  studentInfo: Student;

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
    private student:StudentService,
    private router: Router
  ) {
    this.student.getStudentInfo().subscribe(res=>{
      this.studentInfo=res;
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
      username: this.studentInfo.name,
      school: this.studentInfo.school,
      department: this.studentInfo.department,
      email: this.studentInfo.email
    })
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.initForm();
  }

  ngOnInit(): void {
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
      this.student.updateInfo({
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
              this.router.navigate(['/','student','info']);
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
}
