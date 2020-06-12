export interface Projects {
  id:string;
  name:string;
  description:string;//项目描述
  start_time:string;//项目开始时间
  end_time:string;//项目结束时间，如2020-06-11
  isAdd:string;//是否加入该项目，true或false
  role:string;//1-组长或2-组员
  score:string;//评分方式：true-有互评  false-无互评
}
