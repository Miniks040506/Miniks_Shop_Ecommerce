package com.miniks.shop.service;

import com.miniks.shop.entity.Home;
import com.miniks.shop.entity.HomeCategory;

import java.util.List;

public interface HomeService {

    Home createHomePageData(List<HomeCategory> categories);

}
