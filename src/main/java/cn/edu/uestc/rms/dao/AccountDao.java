package cn.edu.uestc.rms.dao;

import cn.edu.uestc.rms.model.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountDao {
    Account query(@Param("accountNum") String accountNum);

    int createAccount(@Param("account") Account account);
}
