package cn.edu.uestc.rms.VO;

import lombok.Data;

@Data
public class OrderDetailVO {
    private Integer orderId;
    private Integer foodId;
    private String foodName;
    private String foodSize;
    private Integer foodNum;
    private Boolean type;
    private Integer mealCode;
    private Integer position;
    private String phone;
    private String addr;
}
