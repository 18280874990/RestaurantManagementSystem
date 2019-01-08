package cn.edu.uestc.rms.service.impl;

import cn.edu.uestc.rms.Request.OrderConfirmRequest;
import cn.edu.uestc.rms.Request.OrderRequest;
import cn.edu.uestc.rms.VO.OrderDetailVO;
import cn.edu.uestc.rms.VO.OrderVO;
import cn.edu.uestc.rms.dao.AccountDao;
import cn.edu.uestc.rms.dao.EmployeeDao;
import cn.edu.uestc.rms.dao.FoodDao;
import cn.edu.uestc.rms.dao.OrderDao;
import cn.edu.uestc.rms.model.Account;
import cn.edu.uestc.rms.model.Employee;
import cn.edu.uestc.rms.model.Food;
import cn.edu.uestc.rms.model.Order;
import cn.edu.uestc.rms.query.EmployeeQuery;
import cn.edu.uestc.rms.query.FoodQuery;
import cn.edu.uestc.rms.query.OrderQuery;
import cn.edu.uestc.rms.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    public static int MAX_POSITION = 50;
    public static int MEALCODE;
    public static Map<Integer, Integer> POSITIONS = new HashMap<>();

    static {
        for (int i = 1; i <= OrderServiceImpl.MAX_POSITION; i++) {
            OrderServiceImpl.POSITIONS.put(i, 0);
        }
    }

    @Resource
    private OrderDao orderDao;

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private FoodDao foodDao;

    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional
    public boolean confirm(OrderConfirmRequest orderConfirmRequest, HttpSession session) {
        Account account = accountDao.query((String)session.getAttribute("account"));
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderId(orderConfirmRequest.getOrderId());
        orderQuery.setFoodId(orderConfirmRequest.getFoodId());
        Order order = orderDao.query(orderQuery).get(0);

        int employeeId = account.getEmployeeId();
        EmployeeQuery query = new EmployeeQuery();
        query.setEmployeeId(employeeId);
        Employee employee = employeeDao.query(query).get(0);
        if (employee.getType().equals("waiter")) {
            if (order.getType()) {
                order.setState(2);
            } else {
                order.setState(4);
            }
        } else if (employee.getType().equals("cook")) {
            order.setState(1);
        }

        orderDao.updateState(order);

        return true;
    }

    @Override
    public List<OrderDetailVO> list(String accountNum) {
        Account account = accountDao.query(accountNum);
        OrderQuery orderQuery = new OrderQuery();
        if (account.getType().equals("customer")) {
            orderQuery.setAccountNum(accountNum);
        } else {
            int employeeId = account.getEmployeeId();
            EmployeeQuery query = new EmployeeQuery();
            query.setEmployeeId(employeeId);
            Employee employee = employeeDao.query(query).get(0);
            if (employee.getType().equals("waiter")) {
                orderQuery.setWaiterId(employeeId);
                orderQuery.setState(0);
            } else if (employee.getType().equals("cook")) {
                orderQuery.setCookId(employeeId);
                orderQuery.setState(1);
            }
        }
        List<Order> orders = orderDao.query(orderQuery);
        List<OrderDetailVO> orderDetailVOS = new ArrayList<>();
        for (Order order : orders) {
            OrderDetailVO orderDetailVO = new OrderDetailVO();
            BeanUtils.copyProperties(order, orderDetailVO);
            FoodQuery foodQuery = new FoodQuery();
            foodQuery.setFoodId(order.getFoodId());
            Food food = foodDao.query(foodQuery).get(0);
            orderDetailVO.setFoodName(food.getName());
            orderDetailVOS.add(orderDetailVO);
        }

        return orderDetailVOS;
    }

    @Override
    @Transactional
    public OrderVO createOrders(List<OrderRequest> orderRequests, HttpSession session) {
        if (CollectionUtils.isEmpty(orderRequests)) {
            return null;
        }

        Integer maxOrderId = orderDao.queryMaxOrderId();
        maxOrderId = (maxOrderId == null ? 0 : maxOrderId) + 1;

        EmployeeQuery query = new EmployeeQuery();
        query.setType("cook");
        List<Employee> cooks = employeeDao.queryMinNum(query);
        if (CollectionUtils.isEmpty(cooks)) {
            return null;
        }
        Employee cook = cooks.get(0);
        int cookId = cook.getEmployeeId();

        query.setType("waiter");
        List<Employee> waiters = employeeDao.queryMinNum(query);
        if (CollectionUtils.isEmpty(waiters)) {
            return null;
        }
        Employee waiter = waiters.get(0);
        int waiterId = waiter.getEmployeeId();

        int mealCode = 0, position = 0;

        OrderVO orderVO = new OrderVO();
        if (orderRequests.get(0).getType() == true) {
            mealCode = ++OrderServiceImpl.MEALCODE;
            orderVO.setMealCode(mealCode);
            for (int i = 1; i <= OrderServiceImpl.MAX_POSITION; i++) {
                if (OrderServiceImpl.POSITIONS.get(i) == 0) {
                    OrderServiceImpl.POSITIONS.put(i, 1);
                    position = i;
                    orderVO.setPosition(position);
                    break;
                }
            }
        }

        int num = 0;
        for (OrderRequest orderRequest : orderRequests) {
            Order order = new Order();
            BeanUtils.copyProperties(orderRequest, order);
            order.setAccountNum((String)session.getAttribute("account"));
            order.setOrderId(maxOrderId);
            order.setCookId(cookId);
            order.setWaiterId(waiterId);
            order.setMealCode(mealCode);
            order.setPosition(position);
            num = num + orderDao.createOrder(order);

            Food food = new Food();
            food.setFoodId(orderRequest.getFoodId());
            food.setSales(orderRequest.getFoodNum());
            foodDao.updateFood(food);
        }

        cook.setNum(cook.getNum() + 1);
        waiter.setNum(waiter.getNum() + 1);

        employeeDao.updateNum(cook);
        employeeDao.updateNum(waiter);

        return orderVO;
    }
}
