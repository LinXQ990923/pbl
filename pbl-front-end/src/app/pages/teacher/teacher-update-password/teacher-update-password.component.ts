import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {NzModalService} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {TeacherService} from "../../../service/teacher.service";
import {Md5} from "ts-md5";

@Component({
  selector: 'app-teacher-update-password',
  templateUrl: './teacher-update-password.component.html',
  styleUrls: ['./teacher-update-password.component.css']
})
export class TeacherUpdatePasswordComponent implements OnInit {
  validateForm: FormGroup;

  passwordValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<6) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>20){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  oldpasswordValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<6) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>20){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  confirmValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value!== this.validateForm.controls.password.value) {
        observer.next({ error: true, confirm: true });
      }else observer.next(null);
      observer.complete();
    });

  constructor(
    private fb: FormBuilder,
    private modal: NzModalService,
    private router: Router,
    private teacher:TeacherService
  ) {
    this.validateForm = this.fb.group({
      oldpassword: [null, [
        Validators.required,
      ],[
        this.oldpasswordValidator
      ]],
      password: [null, [
        Validators.required,
      ],[
        this.passwordValidator
      ]],
      confirm: ['', [
        Validators.required,
      ],[
        this.confirmValidator
      ]],
    });
  }

  ngOnInit(): void {
  }

  validateConfirmPassword(): void {
    setTimeout(() => this.validateForm.controls.confirm.updateValueAndValidity());
  }

  submitForm():void{
    if (this.validateForm.valid) {
      this.teacher.updatePassword({
        oldPassword: Md5.hashStr(this.validateForm.value.oldpassword),
        newPassword: Md5.hashStr(this.validateForm.value.password)
      }).subscribe(result => {
        if (result.status == "true") {
          this.modal.success({
            nzTitle: '成功',
            nzContent: '修改密码成功',
            nzOnOk: () => {
              this.router.navigate(['/', 'teacher', 'info']);
            }
          });
        } else {
          this.modal.error({
            nzTitle: '失败',
            nzContent: result.message,
          });
        }
      });
    }
  }

}
