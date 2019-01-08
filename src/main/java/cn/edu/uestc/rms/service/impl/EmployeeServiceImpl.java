package cn.edu.uestc.rms.service.impl;

import cn.edu.uestc.rms.Request.EmployeeRequest;
import cn.edu.uestc.rms.Request.SignRequest;
import cn.edu.uestc.rms.dao.EmployeeDao;
import cn.edu.uestc.rms.model.Employee;
import cn.edu.uestc.rms.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private AccountServiceImpl accountService;

    @Override
    @Transactional
    public boolean createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(employeeRequest.getBirth());
            employee.setBirth(date);
        } catch (ParseException e) {
            return false;
        }
        if (employeeDao.add(employee) <= 0) {
            return false;
        }

        SignRequest signRequest = new SignRequest();
        signRequest.setAccountNum(employeeRequest.getPhone());
        signRequest.setPassword("password" + employeeRequest.getPhone());
        accountService.register(signRequest, "employee", employee.getEmployeeId());

        return true;
    }
}
