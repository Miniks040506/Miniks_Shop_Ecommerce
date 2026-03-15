package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Review;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.ReviewRepository;
import com.miniks.shop.request.CreateReviewRequest;
import com.miniks.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest request, User user, Product product) {
        return null;
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return List.of();
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double reviewRating, Long userId) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {

    }
}
