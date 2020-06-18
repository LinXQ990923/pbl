package fudan.edu.pbl.controller;

import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.StudentResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @RequestMapping(value = "/student/info", method = RequestMethod.GET)
    public StudentResponse getInfo(){
        return null;
    }

    @RequestMapping(value = "/student/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(){
        return null;
    }

    @RequestMapping(value = "/student/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(){
        return null;
    }

    @RequestMapping(value = "/student/password/update", method = RequestMethod.POST)
    public ResultResponse updatePassword(){
        return null;
    }

}
