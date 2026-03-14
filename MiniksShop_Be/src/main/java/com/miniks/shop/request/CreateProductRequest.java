package com.miniks.shop.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String title;
    private String description;
    private long mrpPrice;
    private long sellingPrice;
    private String color;
    private List<String> images;
    private String categoryL1;
    private String categoryL2;
    private String categoryL3;
    private String sizes;

}
