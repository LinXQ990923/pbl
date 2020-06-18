package fudan.edu.pbl.controller;

import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.TeacherResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @RequestMapping(value = "/teacher/info", method = RequestMethod.GET)
    public TeacherResponse getInfo(){
        return null;
    }

    @RequestMapping(value = "/teacher/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(){
        return null;
    }

    @RequestMapping(value = "/teacher/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(){
        return null;
    }

    @RequestMapping(value = "/teacher/password/update", method = RequestMethod.POST)
    public ResultResponse updateRassword(){
        return null;
    }

}
