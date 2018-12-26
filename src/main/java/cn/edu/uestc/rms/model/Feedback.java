package cn.edu.uestc.rms.model;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer feedbackId;
    private String account;
    private String content;
    private Date time;
    private String reply;
    private Date replyTime;
}
