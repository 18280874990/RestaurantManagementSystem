package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class User {
    private String account;
    private String password;
    private String people;
    private int employeeId;
    private int customerId;
}
