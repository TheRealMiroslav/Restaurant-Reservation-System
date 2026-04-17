package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Recenze;

public class RecenzeDaoImpl implements RecenzeDao {
    @Override
    public void vytvoritRecenzi(Recenze recenze) throws Exception {
        String sql = "INSERT INTO Recenze (zakaznikId, restauraceId, komentar, hodnoceni) VALUES (?, ?, ?, ?)";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recenze.getZakaznikId());
            stmt.setInt(2, recenze.getRestauraceId());
            stmt.setString(3, recenze.getSlovniHodnoceni());
            stmt.setDouble(4, recenze.getHodnoceni());

            stmt.executeUpdate();
        }
    }

    @Override
    public void smazatRecenzi(int id) throws Exception {
        String sql = "DELETE FROM Recenze WHERE id LIKE ?";

        try (var conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}