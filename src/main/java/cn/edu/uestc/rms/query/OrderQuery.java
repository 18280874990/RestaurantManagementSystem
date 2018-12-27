package cn.edu.uestc.rms.query;

import lombok.Data;

@Data
public class OrderQuery {

    private Integer orderId;

    private Integer foodId;

    private String accountNum;

    private Boolean type;

    private Integer state;

    private Integer cookId;

    private Integer waiterId;
}
