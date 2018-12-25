package cn.edu.uestc.rms.dao;

import cn.edu.uestc.rms.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    String query(@Param("account") String account);
    int createUser(@Param("user") User user);
}
