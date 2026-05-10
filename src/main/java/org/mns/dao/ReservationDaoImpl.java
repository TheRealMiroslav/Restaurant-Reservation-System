package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.factory.ReservationStateFactory;
import org.mns.model.Reservation;

import java.sql.Connection;
import java.util.List;

/**
 * Implementace rozhraní {@link ReservationDao} využívající JDBC pro přístup k databázi.
 */
public class ReservationDaoImpl implements ReservationDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Reservation reservation) throws Exception {
        String sql = "INSERT INTO reservation (customer_id, table_id, start_time, end_time, notes, person_count, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getCustomerId());
            stmt.setInt(2, reservation.getTableId());
            stmt.setTimestamp(3, reservation.getStartTime());
            stmt.setTimestamp(4, reservation.getEndTime());
            stmt.setString(5, reservation.getComment());
            stmt.setInt(6, reservation.getNumOfPeople());
            stmt.setString(7, reservation.getStatus().getName());

            stmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(Reservation reservation) throws Exception {
        String sql = "UPDATE reservation SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getStatus().getName());
            stmt.setInt(2, reservation.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reservation> getReservationByCustomerId(int customerId) throws Exception {
        List<Reservation> reservationList = new java.util.ArrayList<>();

        String sql = "SELECT res.*, t.table_code, t.capacity, r.name AS rest_name " + "FROM reservation res " + "JOIN restaurant_table t ON res.table_id = t.id " + "JOIN restaurant r ON t.restaurant_id = r.id " + "WHERE res.customer_id = ?";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();

                reservation.setId(rs.getInt("id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setTableId(rs.getInt("table_id"));
                reservation.setStartTime(rs.getTimestamp("start_time"));
                reservation.setEndTime(rs.getTimestamp("end_time"));
                reservation.setComment(rs.getString("notes"));
                reservation.setNumOfPeople(rs.getInt("person_count"));

                reservation.setStatus(ReservationStateFactory.getState(rs.getString("status")));

                reservation.setRestaurantName(rs.getString("rest_name"));
                reservation.setTableCode(rs.getString("table_code"));
                reservation.setTableCapacity(rs.getInt("capacity"));

                reservationList.add(reservation);
            }
        }

        return reservationList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkForReservation(int customerId, int restaurantId) throws Exception {
        String sql = "SELECT COUNT(*) AS reservationCount FROM reservation r " + "JOIN restaurant_table s ON r.table_id = s.id " + "WHERE r.customer_id = ? AND s.restaurant_id = ? AND r.status = 'PROBEHLA'";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, restaurantId);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("reservationCount") > 0;
            }
        }

        return false;
    }
}