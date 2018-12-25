package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Revenue {
    private int revenueId;
    private int orderId;
    private double money;
    private String time;
}
