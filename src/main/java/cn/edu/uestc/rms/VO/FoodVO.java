package cn.edu.uestc.rms.VO;

import lombok.Data;

import java.util.Map;

@Data
public class FoodVO {
    private Integer foodId;
    private String name;
    private Map<String, Double> sizePrice;
    private Integer sales;
    private String image;
    private String description;
    private String type;
}
