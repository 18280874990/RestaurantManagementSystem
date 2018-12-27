package cn.edu.uestc.rms.service.impl;

import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.VO.SignInVO;
import cn.edu.uestc.rms.VO.SignUpVO;
import cn.edu.uestc.rms.dao.AccountDao;
import cn.edu.uestc.rms.dao.EmployeeDao;
import cn.edu.uestc.rms.model.Account;
import cn.edu.uestc.rms.model.Employee;
import cn.edu.uestc.rms.query.EmployeeQuery;
import cn.edu.uestc.rms.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public SignInVO signIn(SignRequest signRequest, HttpSession session) {
        String accountNum = signRequest.getAccountNum();
        SignInVO signInVO = new SignInVO();
        Account account = accountDao.query(accountNum);
        if(account == null){
            signInVO.setSuccess(false);
            signInVO.setMsg("账号不存在");
            return signInVO;
        }
        if(signRequest.getPassword().equals(account.getPassword())) {
            signInVO.setSuccess(true);
            signInVO.setType(account.getType());
            if(account.getType().equals("employee")){
                int employeeId = account.getEmployeeId();
                EmployeeQuery query = new EmployeeQuery();
                query.setEmployeeId(employeeId);
                Employee employee = employeeDao.query(query).get(0);
                signInVO.setAccountNum(accountNum);
                signInVO.setType(employee.getType());
            }
            session.setAttribute(account.getAccountNum(), signInVO.getType());
        } else {
            signInVO.setSuccess(false);
            signInVO.setMsg("密码错误");
        }
        return signInVO;
    }

    @Override
    public SignUpVO register(SignRequest signRequest) {
        return register(signRequest, "customer", null);
    }

    public SignUpVO register(SignRequest signRequest, String type, Integer employeeId){
        Account account = new Account();
        BeanUtils.copyProperties(signRequest, account);
        account.setType(type);
        if(employeeId != null){
            account.setEmployeeId(employeeId);
        }
        SignUpVO signUpVO = new SignUpVO();
        if(accountDao.query(signRequest.getAccountNum()) != null){
            signUpVO.setSuccess(false);
            signUpVO.setMsg("账号重复");
        }
        else {
            if (accountDao.createAccount(account) <= 0) {
                signUpVO.setSuccess(false);
                signUpVO.setMsg("注册失败");
            }
            else {
                signUpVO.setSuccess(true);
                signUpVO.setMsg("注册成功");
            }
        }
        return signUpVO;
    }
}
