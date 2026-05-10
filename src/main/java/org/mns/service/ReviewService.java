package org.mns.service;

import org.mns.model.Review;
import org.mns.observer.ReviewObserver;

import java.util.List;

/**
 * Služba pro správu recenzí.
 * Implementuje logiku Observer patternu pro notifikaci o nových recenzích.
 */
public interface ReviewService {
    /**
     * Vytvoří novou recenzi a notifikuje registrované pozorovatele.
     *
     * @param customerId   ID zákazníka.
     * @param restaurantId ID restaurace.
     * @param rating       Hodnocení (typicky 1-5).
     * @param comment      Textový komentář.
     * @throws Exception Pokud zákazník nemá v restauraci proběhlou rezervaci nebo při chybě DB.
     */
    void createReview(int customerId, int restaurantId, double rating, String comment) throws Exception;

    /**
     * Zaregistruje nového pozorovatele událostí.
     *
     * @param observer Pozorovatel implementující {@link ReviewObserver}.
     */
    void addObserver(ReviewObserver observer);

    /**
     * Vrátí seznam recenzí konkrétního zákazníka.
     *
     * @param customerId ID zákazníka.
     * @return Seznam recenzí.
     * @throws Exception Při chybě DB.
     */
    List<Review> getCustomerReviews(int customerId) throws Exception;
}
