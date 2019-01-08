package cn.edu.uestc.rms.Request;

import lombok.Data;

@Data
public class OrderRequest {

    private Integer foodId;

    //private String accountNum;

    private Integer foodNum;

    private String size;

    private Double price;

    private Boolean type;

    private String phone;

    private String addr;
}
