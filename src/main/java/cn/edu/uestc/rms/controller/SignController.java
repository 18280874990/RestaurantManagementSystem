package cn.edu.uestc.rms.controller;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.VO.ResponseVO;
import cn.edu.uestc.rms.VO.SignInVO;
import cn.edu.uestc.rms.VO.SignUpVO;
import cn.edu.uestc.rms.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rms/sign")
public class SignController {

    @Resource
    private AccountService userService;

    @RequestMapping(value = "in", method = RequestMethod.POST)
    public ResponseVO<SignInVO> in(@RequestBody SignRequest signRequest, HttpSession session) {
        return new ResponseVO<SignInVO>().success(userService.signIn(signRequest, session));
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    @Transactional
    public ResponseVO<SignUpVO> up(@RequestBody SignRequest signRequest) {
        return new ResponseVO<SignUpVO>().success(userService.register(signRequest));
    }
}
