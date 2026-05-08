package org.mns.service;

import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> searchRestaurants(String text) throws Exception;

    List<Table> getTables(int restaurantId) throws Exception;

    Restaurant getRestaurantByTableId(int tableId) throws Exception;
}
