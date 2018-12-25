package cn.edu.uestc.rms.service.impl;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.dao.UserDao;
import cn.edu.uestc.rms.model.User;
import cn.edu.uestc.rms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean signIn(SignRequest signRequest) {
        String account = signRequest.getAccount();
        if(signRequest.getPassword().equals(userDao.query(account))){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean register(SignRequest signRequest) {
        User user = new User();
        BeanUtils.copyProperties(signRequest, user);
        user.setPeople("customer");
        if(userDao.createUser(user) <= 0)
            return false;
        else
            return true;
    }
}
