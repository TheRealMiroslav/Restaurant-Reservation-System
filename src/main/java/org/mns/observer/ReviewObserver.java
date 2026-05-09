package org.mns.observer;

import org.mns.model.Review;

/**
 * Rozhraní pro pozorovatele událostí spojených s recenzemi.
 * Součást implementace návrhového vzoru Observer.
 */
public interface ReviewObserver {
    /**
     * Metoda volaná při vytvoření nové recenze.
     *
     * @param review Objekt nově vytvořené recenze.
     */
    void onReviewCreated(Review review);
}
