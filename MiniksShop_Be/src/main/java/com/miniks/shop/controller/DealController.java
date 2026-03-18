package com.miniks.shop.controller;

import com.miniks.shop.entity.Deal;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDealHandler(
            @RequestBody Deal deal
    ) {

        Deal createdDeal = dealService.createDeal(deal);

        return new ResponseEntity<>(createdDeal, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDealHandler(
            @PathVariable long id,
            @RequestBody Deal deal
    ) throws Exception {

        Deal updatedDeal = dealService.updateDeal(deal, id);

        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDealHandler(
            @PathVariable long id
    ) throws Exception {

        dealService.deleteDeal(id);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Deal deleted successfully");

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

}
