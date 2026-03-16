package com.miniks.shop.controller;

import com.miniks.shop.repository.HomeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@RequestMapping
public class CustomerController {
    
    private final HomeCategoryRepository homeCategoryRepository;

}
