package org.mns.service;

import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;

/**
 * Služba pro správu informací o restauracích a jejich stolech.
 */
public interface RestaurantService {
    /**
     * Vyhledá restaurace podle textu v názvu nebo adrese.
     *
     * @param text Hledaný řetězec.
     * @return Seznam nalezených restaurací.
     * @throws Exception Při chybě DB.
     */
    List<Restaurant> searchRestaurants(String text) throws Exception;

    /**
     * Načte všechny stoly pro danou restauraci.
     *
     * @param restaurantId ID restaurace.
     * @return Seznam stolů.
     * @throws Exception Při chybě DB.
     */
    List<Table> getTables(int restaurantId) throws Exception;

    /**
     * Získá restauraci, ke které patří daný stůl.
     *
     * @param tableId ID stolu.
     * @return Objekt Restaurant.
     * @throws Exception Při chybě DB.
     */
    Restaurant getRestaurantByTableId(int tableId) throws Exception;
}
