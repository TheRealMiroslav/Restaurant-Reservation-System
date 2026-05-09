package org.mns.service;

import org.mns.dao.ReservationDao;
import org.mns.dao.ReviewDao;
import org.mns.model.Review;
import org.mns.observer.ReviewObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementace služby pro správu recenzí.
 * Zajišťuje validaci nároku na recenzi a notifikaci pozorovatelů.
 */
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;
    private final ReservationDao reservationDao;

    private final List<ReviewObserver> observers = new ArrayList<>();

    public ReviewServiceImpl(ReviewDao reviewDao, ReservationDao reservationDao) {
        this.reviewDao = reviewDao;
        this.reservationDao = reservationDao;
    }

    /**
     * {@inheritDoc}
     * Provádí kontrolu nároku na recenzi (zda zákazník již restauraci navštívil).
     */
    public void createReview(int customerId, int restaurantId, double rating, String comment) throws Exception {
        // kontrola, zda uživatel měl rezervaci v této restauraci (UC-05 krok 1, výjimka E1)
        boolean visited = reservationDao.checkForReservation(customerId, restaurantId);
        if (!visited) {
            throw new IllegalArgumentException("Recenzi lze napsat pouze po uskutečněné návštěvě restaurant.");
        }

        Review review = new Review(customerId, restaurantId, rating, comment);
        reviewDao.createReview(review);

        notifyObservers(review);
    }

    /**
     * Notifikuje všechny registrované pozorovatele o vytvoření nové recenze.
     *
     * @param review Objekt nové recenze.
     */
    private void notifyObservers(Review review) {
        for (ReviewObserver observer : observers) {
            observer.onReviewCreated(review);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addObserver(ReviewObserver observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    public List<Review> getCustomerReviews(int customerId) throws Exception {
        return reviewDao.getReviewsByCustomerId(customerId);
    }
}