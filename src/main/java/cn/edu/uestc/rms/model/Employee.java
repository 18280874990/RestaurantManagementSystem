package cn.edu.uestc.rms.model;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Integer employeeId;
    private String name;
    private String type;
    private Integer num;
    private Boolean sex;
    private Date birth;
    private Double salary;
    private String phone;
    private String workContent;
}
