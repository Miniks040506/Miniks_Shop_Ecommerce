package com.miniks.shop.controller;

import com.miniks.shop.domain.AccountStatus;
import com.miniks.shop.entity.HomeCategory;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.SellerException;
import com.miniks.shop.service.HomeCategoryService;
import com.miniks.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final SellerService sellerService;
    private final HomeCategoryService homeCategoryService;

    @PatchMapping("/seller/{sellerId}/status/{sellerStatus}")
    public ResponseEntity<Seller> updateSellerStatusHandler(
            @PathVariable Long sellerId,
            @PathVariable AccountStatus sellerStatus
            ) throws SellerException {

        Seller updatedSeller = sellerService
                .updateSellerAccountStatus(sellerId, sellerStatus);

        return ResponseEntity.ok(updatedSeller);
    }

    @GetMapping("/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategoriesHandler()
            throws Exception {

        List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();

        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategoryHandler(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory
    ) throws Exception {

        HomeCategory updatedCategory = homeCategoryService
                .updateHomeCategory(homeCategory, id);

        return ResponseEntity.ok(updatedCategory);
    }

}
