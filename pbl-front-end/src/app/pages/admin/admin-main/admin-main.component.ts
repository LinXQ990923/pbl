import { Component, OnInit } from '@angular/core';
import {Teacher} from "../../../share/teacher";
import {TeacherService} from "../../../service/teacher.service";
import {NzModalService} from "ng-zorro-antd";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit {
  isCollapsed = false;

  constructor(
    private modal:NzModalService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  logout(){
    this.router.navigate(['/','auth','admin','login']);
  }

}
