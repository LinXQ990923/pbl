export interface TaskFinish {
  id:string;//任务id
  name:string;//任务名
  finished:string[];//已完成的组员名字
  not_finished:string[];
}
