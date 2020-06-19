package fudan.edu.pbl.controller;


import fudan.edu.pbl.entity.File;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired FileService fileService;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPhoto(
            @RequestParam(value = "upload_file")MultipartFile file){
        String imageName = null;
        Map<String,String>map=new HashMap<>();
        try {
            imageName = ResourceUtils.getURL("classpath:").getPath()+"static/up/";
        }catch (FileNotFoundException e){
            map.put("status","false");
            map.put("message","上传图片失败");
            map.put("path","invalid");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        String path=fileService.uploadImage(file,imageName);

        if (path==null){
            map.put("state","false");
            map.put("message","上传图片失败");
            map.put("path","invalid");
        }else {
            map.put("state","true");
            map.put("message","上传图片成功");
            map.put("path",String.format("/up/%s", path));
        }
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @RequestMapping(value = "/area/upload",method = RequestMethod.POST)
    public ResponseEntity<Object> uploadFile(
            @RequestParam(value = "upload_file")MultipartFile file, HttpSession session){
        String imageName = null;
        try {
            imageName = ResourceUtils.getURL("classpath:").getPath()+"static/up/";
        }catch (FileNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        System.out.println("File upload: "+imageName);
        String path=fileService.uploadImage(file,imageName);

        File file1=new File();
        file1.setFileName(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('\\')+1));
        file1.setFilePath(String.format("/up/%s", path));
        file1.setProgramID(1);
        file1.setUploadUserID(session.getAttribute("id").toString());
        file1.setUploadTime(LocalDateTime.now());
        if (fileService.save(file1)){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/download")
    public ResultResponse download(@RequestParam (value = "file_name")String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        String filePath = null;
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath()+"static/up/93e445c3f.png";
        }catch (FileNotFoundException e){
            return new ResultResponse("false","file error");
        }
        System.out.println(filePath);
        java.io.File file=new java.io.File(filePath);
        if (file.exists()){
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(fileName,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResultResponse("true","download successfully");
    }

    @RequestMapping("/delete")
    public ResultResponse delete(@RequestParam (value = "file_id")String fileId){
        if(fileService.removeById(fileId)){
            return new ResultResponse("true","delete successfully");
        }else {
            return new ResultResponse("false","database error");
        }
    }

}
