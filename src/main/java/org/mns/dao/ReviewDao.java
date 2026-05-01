package org.mns.dao;

import org.mns.model.Review;

import java.util.List;

public interface ReviewDao {
    void createReview(Review review) throws Exception;

    void deleteReview(int id) throws Exception;

    List<Review> getReviewsByRestaurantId(int restaurantId) throws Exception;

    List<Review> getReviewsByCustomerId(int customerId) throws Exception;
}