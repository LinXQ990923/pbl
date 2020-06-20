import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs";
import {NzModalService} from "ng-zorro-antd";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {Md5} from "ts-md5";
import {Student} from "../../../share/student";

@Component({
  selector: 'app-admin-students',
  templateUrl: './admin-students.component.html',
  styleUrls: ['./admin-students.component.css']
})
export class AdminStudentsComponent implements OnInit {
  validateForm: FormGroup;
  listOfData:Student[];

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
      email: ['', [
        Validators.email,
        Validators.required
      ]],
    });
  }

  ngOnInit(): void {
    this.auth.getAllStudent().subscribe(res=>{
      this.listOfData=res;
    })
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
        this.modal.success({
          nzTitle: '添加成功',
          nzContent: result.message
        });
        this.auth.getAllStudent().subscribe(res=>{
          this.listOfData=res;
        })
      }
      else{
        this.modal.error({
          nzTitle: '添加失败',
          nzContent: result.message
        });
      }
    });
  }

  deleteStudent(id):void{
    this.auth.deleteStudent(id).subscribe(res=>{
      if(res.status === "true"){
        this.modal.success({
          nzTitle: '删除成功',
          nzContent: res.message
        });
        this.auth.getAllStudent().subscribe(result=>{
          this.listOfData=result;
        })
      }
      else{
        this.modal.error({
          nzTitle: '删除失败',
          nzContent: res.message
        });
      }
    })
  }
}
