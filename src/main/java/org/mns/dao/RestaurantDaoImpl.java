package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Restaurant;
import org.mns.model.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public Optional<Restaurant> getRestaurantById(int id) throws Exception {
        String sql = "SELECT * FROM restaurant WHERE id LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = getRestaurants(rs);
                return Optional.of(restaurant);
            }

            return Optional.empty();
        }
    }

    @Override
    public List<Restaurant> getAll() throws Exception {
        List<Restaurant> restaurantList = new ArrayList<>();

        String sql = "SELECT * FROM restaurant";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();

            while (rs.next()) {
                restaurantList.add(getRestaurants(rs));
            }

            return restaurantList;
        }
    }

    @Override
    public List<Restaurant> findByText(String text) throws Exception {
        List<Restaurant> restaurantList = new ArrayList<>();

        String sql = "SELECT * FROM restaurant WHERE name LIKE ? OR address LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + text + "%");
            stmt.setString(2, "%" + text + "%");

            var rs = stmt.executeQuery();

            while (rs.next()) {
                restaurantList.add(getRestaurants(rs));
            }

            return restaurantList;
        }
    }

    private Restaurant getRestaurants(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(rs.getInt("id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setAddress(rs.getString("address"));
        restaurant.setPhoneNumber(rs.getString("phone_number"));
        restaurant.setAverageRating(rs.getDouble("average_rating"));

        return restaurant;
    }

    @Override
    public List<Table> getTablesByRestaurantId(int restauraceId) throws Exception {
        List<Table> stoly = new ArrayList<>();
        String sql = "SELECT * FROM restaurant_table WHERE restaurant_id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restauraceId);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Table s = new Table(rs.getString("table_code"), rs.getInt("capacity"));
                s.setId(rs.getInt("id"));
                stoly.add(s);
            }
        }

        return stoly;
    }

    @Override
    public void updateRestaurantRating(int restaurantId) throws Exception {
        String sql = "UPDATE restaurant SET average_rating = " + "(SELECT AVG(rating) FROM review WHERE restaurant_id = ?) " + "WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);
            stmt.setInt(2, restaurantId);
            stmt.executeUpdate();
        }
    }
}