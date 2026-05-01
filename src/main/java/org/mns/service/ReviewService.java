package org.mns.service;

import org.mns.dao.ReservationDao;
import org.mns.dao.RestaurantDao;
import org.mns.dao.ReviewDao;
import org.mns.model.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDao reviewDao;
    private final RestaurantDao restaurantDao;
    private final ReservationDao reservationDao;

    public ReviewService(ReviewDao reviewDao, RestaurantDao restaurantDao, ReservationDao reservationDao) {
        this.reviewDao = reviewDao;
        this.restaurantDao = restaurantDao;
        this.reservationDao = reservationDao;
    }

    public void createReview(int customerId, int restaurantId, double rating, String comment) throws Exception {
        // kontrola, zda uživatel měl rezervaci v této restauraci (UC-05 krok 1, výjimka E1)
        boolean visited = reservationDao.checkForReservation(customerId, restaurantId);
        if (!visited) {
            throw new IllegalArgumentException("Recenzi lze napsat pouze po uskutečněné návštěvě restaurant.");
        }

        Review review = new Review(customerId, restaurantId, rating, comment);

        reviewDao.createReview(review);

        restaurantDao.updateRestaurantRating(restaurantId);
    }

    public List<Review> getCustomerReviews(int customerId) throws Exception {
        return reviewDao.getReviewsByCustomerId(customerId);
    }
}