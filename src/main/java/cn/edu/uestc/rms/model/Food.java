package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Food {
    private Integer foodId;
    private String name;
    private String sizePrice;
    private Integer sales;
    private String image;
    private String description;
    private String type;
}
