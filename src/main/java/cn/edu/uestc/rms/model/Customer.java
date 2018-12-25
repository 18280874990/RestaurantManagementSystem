package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Customer {
    private int customerId;
    private String name;
    private boolean sex;
    private String phone;
    private String addr;
}
