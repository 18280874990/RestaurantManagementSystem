package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Account {
    private Integer accountId;
    private String accountNum;
    private String password;
    private String type;
    private Integer employeeId;
}
