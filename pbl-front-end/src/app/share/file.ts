export interface File {
  id:string;//文件id
  name:string;//文件名字
  uploader:string;//上传人名字
  date:string;//文件上传时间
  path:string;//文件在服务器的位置
  delete:string;//‘true’或‘false’表示是否有权限删除文件
}
