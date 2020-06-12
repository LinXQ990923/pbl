export interface Comment {
  id:string;//评论id
  author:string;//评论发布人姓名
  date:string;//评论发布时间 例如 2020-06-11
  reply:string;//回复的评论id，如果为第一条发布的，该项为空string“”
  content:string;//评论内容
}
