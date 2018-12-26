package cn.edu.uestc.rms.service.impl;

import cn.edu.uestc.rms.Request.FoodRequest;
import cn.edu.uestc.rms.dao.FoodDao;
import cn.edu.uestc.rms.model.Food;
import cn.edu.uestc.rms.query.FoodQuery;
import cn.edu.uestc.rms.service.FoodService;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodDao foodDao;

    public boolean add(FoodRequest foodRequest) {
        if (checkFoodName(foodRequest.getName())) {
            String url = saveImage(foodRequest.getImage());
            Food food = new Food();
            BeanUtils.copyProperties(foodRequest, food);
            food.setImage(url);
            if (foodDao.createFood(food) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFoodName(String name) {
        FoodQuery query = new FoodQuery();
        query.setName(name);
        List<Food> foods = foodDao.query(query);
        if (CollectionUtils.isEmpty(foods)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Food> list() {
        FoodQuery query = new FoodQuery();
        return foodDao.query(query);
    }

    private String saveImage(MultipartFile multipartFile) {
        try {
            String fileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
            String destFileName = "/Users/huangjiujie/IdeaProjects/RestaurantManagementSystem/src/main/resources/static/image"
                    + File.separator + fileName;
            File destFile = new File(destFileName);
            multipartFile.transferTo(destFile);
            return "/static/image/" + fileName;
        } catch (IOException e) {
            return "";
        }
    }
}
