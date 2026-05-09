package org.mns.dao;

import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;
import java.util.Optional;

/**
 * Rozhraní pro přístup k informacím o restauracích a jejich stolech.
 */
public interface RestaurantDao {
    /**
     * Najde restauraci podle ID.
     *
     * @param id ID restaurace.
     * @return Optional s restaurací.
     */
    Optional<Restaurant> getById(int id) throws Exception;

    /**
     * Vrátí seznam všech restaurací v systému.
     *
     * @return List restaurací.
     */
    List<Restaurant> getAll() throws Exception;

    /**
     * Vyhledá restaurace podle textového řetězce (hledá v názvu nebo adrese).
     *
     * @param text Hledaný výraz.
     * @return Seznam odpovídajících restaurací.
     */
    List<Restaurant> findByText(String text) throws Exception;

    /**
     * Načte všechny stoly, které patří dané restauraci.
     *
     * @param restaurantId ID restaurace.
     * @return Seznam objektů Table.
     */
    List<Table> getTablesByRestaurantId(int restaurantId) throws Exception;

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