package cn.edu.uestc.rms.service;

import cn.edu.uestc.rms.Request.FoodRequest;
import cn.edu.uestc.rms.VO.FoodVO;
import cn.edu.uestc.rms.model.Food;

import java.util.List;

public interface FoodService {
    boolean add(FoodRequest foodRequest);
    boolean checkFoodName(String name);
    List<FoodVO> list();
}
