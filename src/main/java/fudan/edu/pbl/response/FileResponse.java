package fudan.edu.pbl.response;

public class FileResponse {

    private String id;//文件id
    private String name;//文件名字
    private String uploader;//上传人名字
    private String date;//文件上传时间
    private String path;//文件在服务器的位置
    private String delete;//‘true’或‘false’表示是否有权限删除文件

    public FileResponse() {
    }

    public FileResponse(String id, String name, String uploader, String date, String path, String delete) {
        this.id = id;
        this.name = name;
        this.uploader = uploader;
        this.date = date;
        this.path = path;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
