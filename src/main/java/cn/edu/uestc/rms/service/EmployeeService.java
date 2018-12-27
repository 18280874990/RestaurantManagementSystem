package cn.edu.uestc.rms.service;

import cn.edu.uestc.rms.Request.EmployeeRequest;

public interface EmployeeService {
    boolean createEmployee(EmployeeRequest employeeRequest);
}
