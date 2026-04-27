package org.mns.dao;

import org.mns.db.DatabaseManager;

public class StulDaoImpl implements StulDao {
    @Override
    public boolean jeStulDostupny(int stulId, String casZacatek, String casKonec) throws Exception {
        String sql = "SELECT COUNT(*) AS pocet FROM Rezervace " + "WHERE stulId = ? " + "AND stav != 'Zrusena' " + "AND cas_zacatek < ? AND cas_konec > ?";

        try (var conn = DatabaseManager.getConnection(); var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stulId);
            pstmt.setString(2, casKonec);
            pstmt.setString(3, casZacatek);

            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("pocet") == 0; // Stůl je dostupný, pokud není žádná kolize
            }

        } catch (Exception e) {
            System.err.println("Chyba při kontrole dostupnosti stolu: " + e.getMessage());
            throw e;
        }

        return false; // Pokud dojde k chybě, považujeme stůl za nedostupný
    }
}