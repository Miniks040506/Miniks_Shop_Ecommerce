package com.miniks.shop.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private double mrpPrice;
    private double sellingPrice;
    private String color;
    private List<String> images;
    private String categoryL1;
    private String categoryL2;
    private String categoryL3;
    private String sizes;

}
