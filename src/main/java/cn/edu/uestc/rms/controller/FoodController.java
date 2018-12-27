package cn.edu.uestc.rms.controller;

import cn.edu.uestc.rms.Request.FoodRequest;
import cn.edu.uestc.rms.VO.FoodVO;
import cn.edu.uestc.rms.VO.ResponseVO;
import cn.edu.uestc.rms.model.Food;
import cn.edu.uestc.rms.service.FoodService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rms/food")
public class FoodController {

    @Resource
    private FoodService foodService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseVO<Boolean> create(FoodRequest foodRequest) {
        return new ResponseVO<Boolean>().success(foodService.add(foodRequest));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseVO<List<FoodVO>> list(FoodRequest foodRequest) {
        return new ResponseVO<List<FoodVO>>().success(foodService.list());
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    ResponseVO<Boolean> check(@RequestParam String name) {
        return new ResponseVO<Boolean>().success(foodService.checkFoodName(name));
    }
}
