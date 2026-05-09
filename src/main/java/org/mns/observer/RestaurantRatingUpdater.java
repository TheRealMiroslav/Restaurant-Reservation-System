package org.mns.observer;

import org.mns.dao.RestaurantDao;
import org.mns.model.Review;

/**
 * Konkrétní pozorovatel, který zajišťuje aktualizaci průměrného hodnocení restaurace
 * po přidání nové recenze.
 */
public class RestaurantRatingUpdater implements ReviewObserver {
    private final RestaurantDao restaurantDao;

    public RestaurantRatingUpdater(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    /**
     * {@inheritDoc}
     * Po vytvoření recenze vyvolá přepočet průměrného hodnocení v databázi pro danou restauraci.
     */
    @Override
    public void onReviewCreated(Review review) {
        try {
            restaurantDao.updateRestaurantRating(review.getRestaurantId());
        } catch (Exception e) {
            System.err.println("Chyba při aktualizaci hodnocení restaurace: " + e.getMessage());
        }
    }
}