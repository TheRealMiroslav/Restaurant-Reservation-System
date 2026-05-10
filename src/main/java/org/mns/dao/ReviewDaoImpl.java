package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementace rozhraní {@link ReviewDao}.
 */
public class ReviewDaoImpl implements ReviewDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public void createReview(Review review) throws Exception {
        String sql = "INSERT INTO review (customer_id, restaurant_id, comment, rating) VALUES (?, ?, ?, ?)";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getCustomerId());
            stmt.setInt(2, review.getRestaurantId());
            stmt.setString(3, review.getComment());
            stmt.setDouble(4, review.getRating());

            stmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> getReviewsByCustomerId(int customerId) throws Exception {
        List<Review> reviewList = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE customer_id = ?";

        return getReviews(customerId, reviewList, sql);
    }

    /**
     * Pomocná metoda pro načtení recenzí z databáze na základě SQL dotazu.
     *
     * @param id         Identifikátor (zákazníka nebo restaurace) použitý v parametru SQL dotazu.
     * @param reviewList Seznam, do kterého se recenze přidají.
     * @param sql        SQL dotaz k provedení.
     * @return Seznam načtených recenzí.
     * @throws Exception Při chybě databáze.
     */
    private List<Review> getReviews(int id, List<Review> reviewList, String sql) throws Exception {
        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setCustomerId(rs.getInt("customer_id"));
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setComment(rs.getString("comment"));
                r.setRating(rs.getDouble("rating"));

                reviewList.add(r);
            }
        }

        return reviewList;
    }
}