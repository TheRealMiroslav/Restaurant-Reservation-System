package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementace rozhraní {@link TableDao}.
 */
public class TableDaoImpl implements TableDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception {
        String sql = "SELECT COUNT(*) AS pocet FROM reservation " + "WHERE table_id = ? " + "AND status != 'ZRUSENA' " + "AND start_time < ? AND end_time > ?";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            stmt.setTimestamp(2, endTime);
            stmt.setTimestamp(3, startTime);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("pocet") == 0; // Stůl je dostupný, pokud není žádná kolize
            }

        } catch (Exception e) {
            System.err.println("Chyba při kontrole dostupnosti stolu: " + e.getMessage());
            throw e;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getById(int id) throws Exception {
        String sql = "SELECT * FROM restaurant_table WHERE id = ?";
        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Table table = new Table(rs.getString("table_code"), rs.getInt("capacity"));
                table.setId(rs.getInt("id"));
                return table;
            }

            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Table> getTablesByRestaurantId(int restaurantId) throws Exception {
        List<Table> tableList = new ArrayList<>();
        String sql = "SELECT * FROM restaurant_table WHERE restaurant_id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Table s = new Table(rs.getString("table_code"), rs.getInt("capacity"));
                s.setId(rs.getInt("id"));
                tableList.add(s);
            }
        }

        return tableList;
    }
}