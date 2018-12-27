package cn.edu.uestc.rms.Request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
public class OrderConfirmRequest {
    private String accountNum;
    private Integer orderId;
    private Integer foodId;
}
