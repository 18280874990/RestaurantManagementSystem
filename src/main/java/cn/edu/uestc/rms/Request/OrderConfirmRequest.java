package cn.edu.uestc.rms.Request;

import lombok.Data;

@Data
public class OrderConfirmRequest {
    //private String accountNum;
    private Integer orderId;
    private Integer foodId;
}
