package com.miniks.shop.service;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Review;
import com.miniks.shop.entity.User;
import com.miniks.shop.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(
            CreateReviewRequest request,
            User user,
            Product product
    );

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId, String reviewText,
                        double reviewRating, Long userId) throws Exception;

//    Review updateReview(Long reviewId, CreateReviewRequest request, Long userId);

    void deleteReview(Long reviewId, Long userId) throws Exception;

    Review getReviewById(Long reviewId) throws Exception;

}
