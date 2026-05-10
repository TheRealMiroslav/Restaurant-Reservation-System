package org.mns.dao;

import org.mns.model.Review;

import java.util.List;

/**
 * Rozhraní pro správu a načítání recenzí restaurací.
 */
public interface ReviewDao {
    /**
     * Vytvoří novou recenzi v systému.
     *
     * @param review Objekt recenze k uložení.
     * @throws Exception Při chybě komunikace s databází.
     */
    void createReview(Review review) throws Exception;

    /**
     * Vrátí seznam všech recenzí napsaných daným zákazníkem.
     *
     * @param customerId ID zákazníka.
     * @return Seznam recenzí.
     * @throws Exception Při chybě komunikace s databází.
     */
    List<Review> getReviewsByCustomerId(int customerId) throws Exception;
}