package cn.edu.uestc.rms.dao;

import cn.edu.uestc.rms.model.Food;
import cn.edu.uestc.rms.query.FoodQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodDao {
    int createFood(@Param("food") Food food);

    List<Food> query(@Param("query") FoodQuery query);

    Integer updateFood(@Param("food") Food food);
}
