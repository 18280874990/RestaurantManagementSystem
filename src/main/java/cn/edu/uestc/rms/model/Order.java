package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Order {
    private int orderId;
    private int menuId;
    private String account;
    private int menuNum;
    private String time;
    private String type;
    private int state;
    private int mealCode;
    private String phone;
    private String addr;
}
