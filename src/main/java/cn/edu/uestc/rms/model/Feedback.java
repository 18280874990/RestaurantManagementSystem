package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Feedback {
    private int feedbackId;
    private int customerId;
    private String content;
    private String time;
}
