package cn.edu.uestc.rms.model;

import lombok.Data;

@Data
public class Menu {
    private int menuId;
    private String name;
    private double price;
    private int sales;
    private String image;
    private String description;
    private String type;
    private int employeeId;
}
