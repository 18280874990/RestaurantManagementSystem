package cn.edu.uestc.rms.controller;

import cn.edu.uestc.rms.Request.EmployeeRequest;
import cn.edu.uestc.rms.VO.ResponseVO;
import cn.edu.uestc.rms.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rms/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseVO<Boolean> create(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseVO<Boolean>().success(employeeService.createEmployee(employeeRequest));
    }
}
