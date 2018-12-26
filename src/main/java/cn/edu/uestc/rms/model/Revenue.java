package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Revenue {
    private Integer revenueId;
    private Integer orderId;
    private Integer money;
    private String time;
}
