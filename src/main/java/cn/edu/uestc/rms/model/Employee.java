package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Employee {
    private int employeeId;
    private String name;
    private String type;
    private boolean sex;
    private double salary;
    private String phone;
}
