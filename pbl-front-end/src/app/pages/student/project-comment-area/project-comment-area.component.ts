import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../../service/project.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Comment} from "../../../share/comment";
import {StudentService} from "../../../service/student.service";
import {Student} from "../../../share/student";
import {NzMessageService, NzModalService} from "ng-zorro-antd";

@Component({
  selector: 'app-project-comment-area',
  templateUrl: './project-comment-area.component.html',
  styleUrls: ['./project-comment-area.component.css']
})
export class ProjectCommentAreaComponent implements OnInit {
  project_id:string;
  comments:Comment[];
  data:[];
  studentInfo:Student;
  reply:'';
  submitting:boolean;

  constructor(
    private project:ProjectService,
    private route:ActivatedRoute,
    private student:StudentService,
    private modal:NzModalService,
    private message: NzMessageService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      this.project_id=params.get("project_id");
      this.project.getComments(this.project_id).subscribe(res=>{
        this.comments=res;
        this.initData();
        this.student.getStudentInfo().subscribe(stu=>{
          this.studentInfo=stu;
        });
      });
    });
  }

  initData():void{
    this.data=[];
    let stack:string[];
    this.data=this.getChild('');
  }

  getChild(id:string,):any{
    let res=[];
    for (let item of this.comments){
      if (item.reply==id){
        res.push({
          id:item.id,
          author: item.author,
          reply: '',
          submitting : false,
          content:item.content,
          datetime: item.date,
          children: this.getChild(item.id)
        });
      }
    }
    return res;
  }

  handleSubmit(comment:any): void {
    const content = comment.reply;
    const formatDate = ( time: any ) => {
      // 格式化日期，获取今天的日期
      const Dates = new Date( time );
      const year: number = Dates.getFullYear();
      const month: any = ( Dates.getMonth() + 1 ) < 10 ? '0' + ( Dates.getMonth() + 1 ) : ( Dates.getMonth() + 1 );
      const day: any = Dates.getDate() < 10 ? '0' + Dates.getDate() : Dates.getDate();
      return year + '-' + month + '-' + day;
    };
    let tmp = formatDate( new Date().getTime() );
    comment.submitting=true;
    comment.reply = '';
    this.project.addComment({
      author_id:this.studentInfo.id,
      author_name:this.studentInfo.name,
      date:tmp,
      reply:comment.id,
      content:content,
      project_id:this.project_id
    }).subscribe(result=>{
      comment.submitting=false;
      if (result.status=='true'){
        this.message.create("success", "回复成功");
        comment.children.push({
          id:result.message,
          author: this.studentInfo.name,
          reply:'',
          submitting:false,
          content:content,
          children:[]
        });
      }else {
        this.message.create("error", "回复失败，请稍后再试");
      }
    });
  }

  addNewComment():void{
    const content = this.reply;
    const formatDate = ( time: any ) => {
      // 格式化日期，获取今天的日期
      const Dates = new Date( time );
      const year: number = Dates.getFullYear();
      const month: any = ( Dates.getMonth() + 1 ) < 10 ? '0' + ( Dates.getMonth() + 1 ) : ( Dates.getMonth() + 1 );
      const day: any = Dates.getDate() < 10 ? '0' + Dates.getDate() : Dates.getDate();
      return year + '-' + month + '-' + day;
    };
    let tmp = formatDate( new Date().getTime() );
    this.submitting=true;
    this.reply = '';
    this.project.addComment({
      author_id:this.studentInfo.id,
      author_name:this.studentInfo.name,
      date:tmp,
      reply:'',
      content:content,
      project_id:this.project_id
    }).subscribe(result=>{
      this.submitting=false;
      if (result.status=='true'){
        this.message.create("success", "发布成功");
        location.reload();
      }else {
        this.message.create("error", "发布失败，请稍后再试");
      }
    });
  }
}
