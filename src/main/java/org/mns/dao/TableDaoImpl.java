package org.mns.dao;

import org.mns.db.DatabaseManager;

import java.sql.Timestamp;

public class TableDaoImpl implements TableDao {
    @Override
    public boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception {
        String sql = "SELECT COUNT(*) AS pocet FROM reservation " + "WHERE table_id = ? " + "AND status != 'Zrusena' " + "AND start_time < ? AND end_time > ?";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            stmt.setTimestamp(2, startTime);
            stmt.setTimestamp(3, endTime);

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
}