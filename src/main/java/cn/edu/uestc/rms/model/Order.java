package cn.edu.uestc.rms.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderId;
    private Integer foodId;
    private String accountNum;
    private Integer foodNum;
    private String size;
    private Double price;
    private Date time;
    private Boolean type;
    private Integer state;
    private Integer cookId;
    private Integer waiterId;
    private Integer mealCode;
    private Integer position;
    private String phone;
    private String addr;
}
