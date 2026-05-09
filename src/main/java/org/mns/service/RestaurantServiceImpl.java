package org.mns.service;

import org.mns.dao.RestaurantDao;
import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;

/**
 * Implementace služby pro vyhledávání restaurací a správu jejich stolů.
 */
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantDao restaurantDao;

    public RestaurantServiceImpl(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Restaurant> searchRestaurants(String text) throws Exception {
        return restaurantDao.findByText(text);
    }

    /**
     * {@inheritDoc}
     */
    public List<Table> getTables(int restaurantId) throws Exception {
        return restaurantDao.getTablesByRestaurantId(restaurantId);
    }

    /**
     * {@inheritDoc}
     */
    public Restaurant getRestaurantByTableId(int tableId) throws Exception {
        return restaurantDao.getRestaurantByTableId(tableId);
    }
}