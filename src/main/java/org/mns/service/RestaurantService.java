package org.mns.service;

import org.mns.dao.RestaurantDao;
import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;

public class RestaurantService {
    private final RestaurantDao restaurantDao;

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public List<Restaurant> searchRestaurants(String text) throws Exception {
        return restaurantDao.findByText(text);
    }

    public List<Table> getTables(int restaurantId) throws Exception {
        return restaurantDao.getTablesByRestaurantId(restaurantId);
    }

    public Restaurant getRestaurantByTableId(int tableId) throws Exception {
        return restaurantDao.getRestaurantByTableId(tableId);
    }
}