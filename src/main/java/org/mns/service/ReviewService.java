package org.mns.service;

import org.mns.dao.RestaurantDao;
import org.mns.dao.ReviewDao;
import org.mns.model.Review;

public class ReviewService {
    private final ReviewDao reviewDao;
    private final RestaurantDao restaurantDao;

    public ReviewService(ReviewDao reviewDao, RestaurantDao restaurantDao) {
        this.reviewDao = reviewDao;
        this.restaurantDao = restaurantDao;
    }

    public void createReview(int customerId, int restaurantId, double rating, String comment) throws Exception {
        Review review = new Review(customerId, restaurantId, rating, comment);

        reviewDao.createReview(review);

        restaurantDao.updateRestaurantRating(restaurantId);
    }
}