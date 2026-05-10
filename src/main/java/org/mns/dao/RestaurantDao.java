package org.mns.dao;

import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;
import java.util.Optional;

public interface RestaurantDao {
    Optional<Restaurant> getById(int id) throws Exception;

    List<Restaurant> getAll() throws Exception;

    List<Restaurant> findByText(String Text) throws Exception;

    List<Table> getTablesByRestaurantId(int restaurantId) throws Exception;

    Restaurant getRestaurantByTableId(int tableId) throws Exception;

    void updateRestaurantRating(int restaurantId) throws Exception;
}