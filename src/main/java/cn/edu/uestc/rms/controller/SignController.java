package cn.edu.uestc.rms.controller;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.VO.ResponseVO;
import cn.edu.uestc.rms.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rms/sign")
public class SignController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "in", method = RequestMethod.POST)
    public ResponseVO<Boolean> in(@RequestBody SignRequest signRequest){
        if(userService.signIn(signRequest) == true){
            return new ResponseVO<Boolean>().success(true);
        }
        else {
            return new ResponseVO<Boolean>().fail("sign in error");
        }
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    @Transactional
    public ResponseVO<Boolean> up(@RequestBody SignRequest signRequest){

        return new ResponseVO<Boolean>().success(userService.register(signRequest));
    }
}
