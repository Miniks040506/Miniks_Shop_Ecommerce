package com.miniks.shop.controller;

import com.miniks.shop.entity.Home;
import com.miniks.shop.entity.HomeCategory;
import com.miniks.shop.service.HomeCategoryService;
import com.miniks.shop.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategoryHandler(
            @RequestBody List<HomeCategory> homeCategories
    ) {

        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);

        Home home = homeService.createHomePageData(homeCategories);

        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

}
