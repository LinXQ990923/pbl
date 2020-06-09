import { Injectable } from '@angular/core';
import {Auth} from "../share/auth";

@Injectable({
  providedIn: 'root'
})
export class MemoryService {

  student='student';
  teacher='teacher';

  constructor() { }
  getRememberStudent():boolean{
    let val = localStorage.getItem(this.student);
    if(!val){
      return false;
    }
    return true;
  }

  setStudent(user:Auth){
    if(user){
      localStorage.setItem(this.student,JSON.stringify(user));
    }
  }

  unsetStudent(){
    if(this.getRememberStudent()){
      localStorage.removeItem(this.student);
    }
  }

  getStudent(){
    let val = localStorage.getItem(this.student);
    if(!val){
      return {
        "userId":"null",
        "password":""
      };
    }
    return JSON.parse(val);
  }

  getRememberTeacher():boolean{
    let val = localStorage.getItem(this.teacher);
    if(!val){
      return false;
    }
    return true;
  }

  setTeacher(user:Auth){
    if(user){
      localStorage.setItem(this.teacher,JSON.stringify(user));
    }
  }

  unsetTeacher(){
    if(this.getRememberTeacher()){
      localStorage.removeItem(this.teacher);
    }
  }

  getTeacher(){
    let val = localStorage.getItem(this.teacher);
    if(!val){
      return {
        "userId":"null",
        "password":""
      };
    }
    return JSON.parse(val);
  }
}
