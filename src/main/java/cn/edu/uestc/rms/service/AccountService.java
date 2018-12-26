package cn.edu.uestc.rms.service;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.VO.SignInVO;
import cn.edu.uestc.rms.VO.SignUpVO;

import javax.servlet.http.HttpSession;

public interface AccountService {

    SignInVO signIn(SignRequest signRequest, HttpSession session);

    SignUpVO register(SignRequest signUpRequest);
}
