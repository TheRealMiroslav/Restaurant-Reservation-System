package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    @Override
    public void createReview(Review review) throws Exception {
        String sql = "INSERT INTO Recenze (zakaznikId, restauraceId, komentar, hodnoceni) VALUES (?, ?, ?, ?)";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getCustomerId());
            stmt.setInt(2, review.getRestaurantId());
            stmt.setString(3, review.getComment());
            stmt.setDouble(4, review.getRating());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteReview(int id) throws Exception {
        String sql = "DELETE FROM Recenze WHERE id LIKE ?";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Review> getReviewsByRestaurantId(int restaurantId) throws Exception {
        List<Review> reviewList = new ArrayList<>();
        String sql = "SELECT * FROM Recenze WHERE restauraceId = ?";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setCustomerId(rs.getInt("zakaznikId"));
                r.setRestaurantId(rs.getInt("restauraceId"));
                r.setComment(rs.getString("komentar"));
                r.setRating(rs.getDouble("hodnoceni"));
                reviewList.add(r);
            }
        }

        return reviewList;
    }
}