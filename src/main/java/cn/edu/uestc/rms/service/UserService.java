package cn.edu.uestc.rms.service;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    Boolean signIn(SignRequest signRequest);

    Boolean register(SignRequest signUpRequest);
}
