package org.mns.dao;

import org.mns.db.DatabaseManager;

import java.sql.Timestamp;

public class TableDaoImpl implements TableDao {
    @Override
    public boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception {
        String sql = "SELECT COUNT(*) AS pocet FROM Rezervace " + "WHERE stulId = ? " + "AND stav != 'Zrusena' " + "AND cas_zacatek < ? AND cas_konec > ?";

        try (var conn = DatabaseManager.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableId);
            pstmt.setTimestamp(2, startTime);
            pstmt.setTimestamp(3, endTime);

            var rs = pstmt.executeQuery();
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