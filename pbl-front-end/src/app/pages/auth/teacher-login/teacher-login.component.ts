import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {MemoryService} from "../../../service/memory.service";
import {NzModalService} from "ng-zorro-antd";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {Md5} from "ts-md5";

@Component({
  selector: 'app-teacher-login',
  templateUrl: './teacher-login.component.html',
  styleUrls: ['./teacher-login.component.css']
})
export class TeacherLoginComponent implements OnInit {

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

  passwordValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      if (control.value.length<6) {
        observer.next({ error: true, minLength: true });
      } else if (control.value.length>20){
        observer.next({ error: true, maxLength: true });
      }else observer.next(null);
      observer.complete();
    });

  constructor(
    private fb: FormBuilder,
    private memory: MemoryService,
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
      password: [null, [
        Validators.required,
      ],[
        this.passwordValidator
      ]],
      remember: [true]
    });
    if (memory.getRememberTeacher()){
      let teacher = memory.getTeacher();
      this.validateForm.setValue({
        userId: teacher.userId,
        password: teacher.password,
        remember: true
      });
    }
  }

  ngOnInit(): void {

  }

  submitForm(): void {
    //console.log(this.validateForm.value);
    if(!this.validateForm.valid){
      this.modal.error({
        nzTitle: '信息有误',
        nzContent: '请按要求填写信息'
      });
      return;
    }

    /* MD5加密 */
    this.auth.loginAsTeacher({
      "id": this.validateForm.value.userId,
      "password": Md5.hashStr(this.validateForm.value.password)
    }).subscribe(result => {
      if(result.status === "true"){
        if(this.validateForm.value.remember){
          this.memory.setTeacher({
            userId:this.validateForm.value.userId,
            password:this.validateForm.value.password
          });
        }else this.memory.unsetTeacher();
        /* 导航到首页 */
        this.router.navigate(['/','teacher','info']);
      }
      else{
        this.modal.error({
          nzTitle: '登陆信息有误',
          nzContent: result.message
        });
      }
    });
  }

}
