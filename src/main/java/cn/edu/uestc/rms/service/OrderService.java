package cn.edu.uestc.rms.service;

import cn.edu.uestc.rms.Request.OrderConfirmRequest;
import cn.edu.uestc.rms.Request.OrderRequest;
import cn.edu.uestc.rms.VO.OrderDetailVO;
import cn.edu.uestc.rms.VO.OrderVO;
import cn.edu.uestc.rms.model.Order;

import java.util.List;

public interface OrderService {
    boolean confirm(OrderConfirmRequest orderConfirmRequest);

    List<OrderDetailVO> list(String accountNum);

    OrderVO createOrders(List<OrderRequest> orderRequests);
}
