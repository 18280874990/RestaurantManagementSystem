package cn.edu.uestc.rms.Request;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {
    private String name;
    private String type;
    private Boolean sex;
    private String birth;
    private Double salary;
    private String phone;
    private String workContent;
}
