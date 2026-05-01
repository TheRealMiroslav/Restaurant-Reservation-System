package org.mns.dao;

import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.util.List;
import java.util.Optional;

public interface RestaurantDao {
    //void pridatRestauraci(Restaurace restaurace);
    //void upravitRestauraci(Restaurace restaurace);
    //void smazatRestauraci(int id);

    public Optional<Restaurant> getRestaurantById(int id) throws Exception;

    public List<Restaurant> getAll() throws Exception;

    public List<Restaurant> findByText(String Text) throws Exception;

    public List<Table> getTablesByRestaurantId(int restaurantId) throws Exception;

    public void updateRestaurantRating(int restaurantId) throws Exception;
}