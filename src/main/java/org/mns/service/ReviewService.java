package org.mns.service;

import org.mns.model.Review;
import org.mns.observer.ReviewObserver;

import java.util.List;

public interface ReviewService {
    void createReview(int customerId, int restaurantId, double rating, String comment) throws Exception;

    void addObserver(ReviewObserver observer);

    List<Review> getCustomerReviews(int customerId) throws Exception;
}
