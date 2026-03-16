package com.miniks.shop.controller;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Review;
import com.miniks.shop.entity.User;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.request.CreateReviewRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.service.ProductService;
import com.miniks.shop.service.ReviewService;
import com.miniks.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getProductReviewsHandler(
            @PathVariable Long productId
    ) throws Exception {

        List<Review> reviews = reviewService.getReviewsByProductId(productId);

        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> writeReviewHandler(
            @RequestBody CreateReviewRequest request,
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Product product = productService.findProductById(productId);

        Review review = reviewService.createReview(
                request,
                user,
                product
        );

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReviewHandler(
            @RequestBody CreateReviewRequest request,
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Review review = reviewService.updateReview(
                reviewId,
                request.getReviewText(),
                request.getReviewRating(),
                user.getId()
        );

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReviewHandler(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        reviewService.deleteReview(reviewId, user.getId());

        ApiResponse response = new ApiResponse();
        response.setMessage("Review deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
