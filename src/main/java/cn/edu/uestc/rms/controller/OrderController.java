package cn.edu.uestc.rms.controller;

import cn.edu.uestc.rms.Request.OrderConfirmRequest;
import cn.edu.uestc.rms.Request.OrderRequest;
import cn.edu.uestc.rms.VO.OrderDetailVO;
import cn.edu.uestc.rms.VO.OrderVO;
import cn.edu.uestc.rms.VO.ResponseVO;
import cn.edu.uestc.rms.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/rms/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseVO<OrderVO> create(@RequestBody List<OrderRequest> requests, HttpSession session){
        return new ResponseVO<OrderVO>().success(orderService.createOrders(requests, session));
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseVO<List<OrderDetailVO>> list(String accountNum){
        return new ResponseVO<List<OrderDetailVO>>().success(orderService.list(accountNum));
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public ResponseVO<Boolean> confirm(@RequestBody OrderConfirmRequest orderConfirmRequest, HttpSession session){
        return new ResponseVO<Boolean>().success(orderService.confirm(orderConfirmRequest, session));
    }
}
