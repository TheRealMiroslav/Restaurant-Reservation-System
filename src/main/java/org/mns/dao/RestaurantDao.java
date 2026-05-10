package org.mns.dao;

import org.mns.model.Restaurant;

import java.util.List;

/**
 * Rozhraní pro přístup k informacím o restauracích a jejich stolech.
 */
public interface RestaurantDao {
    /**
     * Vyhledá restaurace podle textového řetězce (hledá v názvu nebo adrese).
     *
     * @param text Hledaný výraz.
     * @return Seznam odpovídajících restaurací.
     */
    List<Restaurant> findByText(String text) throws Exception;

    /**
     * Získá restauraci, ke které patří konkrétní stůl.
     *
     * @param tableId ID stolu.
     * @return Objekt Restaurant nebo null, pokud nebyl nalezen.
     */
    Restaurant getRestaurantByTableId(int tableId) throws Exception;

    /**
     * Přepočítá průměrné hodnocení restaurace na základě všech uložených recenzí.
     *
     * @param restaurantId ID restaurace k aktualizaci.
     */
    void updateRestaurantRating(int restaurantId) throws Exception;
}