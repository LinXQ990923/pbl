import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {NzModalService} from "ng-zorro-antd";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {Md5} from "ts-md5";

@Component({
  selector: 'app-student-signup',
  templateUrl: './student-signup.component.html',
  styleUrls: ['./student-signup.component.css']
})
export class StudentSignupComponent implements OnInit {
  validateForm: FormGroup;

  userIdValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<6) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>20){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  usernameValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<2) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>6){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  passwordValidator = (control: FormControl) =>
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
    private auth: AuthService,
    private router: Router
  ) {
    this.validateForm = this.fb.group({
      userId: [null, [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9_]*')
      ],
        [
          this.userIdValidator
        ]],
      username:[null, [
        Validators.required,
      ],
        [
          this.usernameValidator
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
      email: ['', [
        Validators.email,
        Validators.required
      ]],
    });
  }

  ngOnInit(): void {

  }

  submitForm(): void {
    console.log(this.validateForm.value);
    if(!this.validateForm.valid){
      this.modal.error({
        nzTitle: '信息有误',
        nzContent: '请按要求填写信息'
      });
      return;
    }

    /* MD5加密 */
    this.auth.signUpAsStudent({
      "id": this.validateForm.value.userId,
      "name": this.validateForm.value.username,
      "password": Md5.hashStr(this.validateForm.value.password),
      "email": this.validateForm.value.email
    }).subscribe(result => {
      if(result.status === "true"){
        /* 导航到首页 */
        this.router.navigate(['/','student','info']);
      }
      else{
        this.modal.error({
          nzTitle: '登陆信息有误',
          nzContent: result.message
        });
      }
    });
  }

  validateConfirmPassword(): void {
    setTimeout(() => this.validateForm.controls.confirm.updateValueAndValidity());
  }
}
