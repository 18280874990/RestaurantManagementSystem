package cn.edu.uestc.rms.dao;

import cn.edu.uestc.rms.model.Order;
import cn.edu.uestc.rms.query.OrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    List<Order> query(@Param("query") OrderQuery query);

    Integer queryMaxOrderId();

    int createOrder(@Param("order") Order order);

    int updateState(@Param("order") Order order);
}
