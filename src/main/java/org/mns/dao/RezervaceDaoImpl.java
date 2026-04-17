package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Rezervace;
import org.mns.model.state.*;

import java.sql.Connection;
import java.util.List;

public class RezervaceDaoImpl implements RezervaceDao {
    @Override
    public void vytvoritRezervaci(Rezervace rezervace) throws Exception {
        String sql = "INSERT INTO Rezervace (zakaznikId, stulId, casZacatek, casKonec, poznamky) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rezervace.getZakaznikId());
            stmt.setInt(2, rezervace.getStulId());
            stmt.setTimestamp(3, rezervace.getCasZacatek());
            stmt.setTimestamp(4, rezervace.getCasKonec());
            stmt.setString(5, rezervace.getPoznamka());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rezervace byla úspěšně vytvořena.");
            } else {
                System.out.println("Nepodařilo se vytvořit rezervaci.");
            }
        }
    }

    @Override
    public void zrusitRezervaci(int id) throws Exception {
        String sql = "UPDATE Rezervace SET stav = 'ZRUSENA' WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rezervace byla úspěšně zrušena.");
            } else {
                System.out.println("Nepodařilo se zrušit rezervaci.");
            }
        }
    }

    @Override
    public List<Rezervace> najdiRezervaceZakaznika(int zakaznikId) throws Exception {
        List<Rezervace> rezervaceList = new java.util.ArrayList<>();

        String sql = "SELECT * FROM Rezervace WHERE zakaznikId = ?";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, zakaznikId);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Rezervace rezervace = new Rezervace();

                rezervace.setId(rs.getInt("id"));
                rezervace.setZakaznikId(rs.getInt("zakaznikId"));
                rezervace.setStulId(rs.getInt("stulId"));
                rezervace.setCasZacatek(rs.getTimestamp("casZacatek"));
                rezervace.setCasKonec(rs.getTimestamp("casKonec"));
                rezervace.setPoznamka(rs.getString("poznamky"));
                rezervace.setStav(stavZRetezce(rs.getString("stav")));

                rezervaceList.add(rezervace);
            }
        }

        return rezervaceList;
    }

    private RezervaceStav stavZRetezce(String stav) {
        return switch (stav) {
            case "POTVRZENA" -> new PotvrzenaStav();
            case "ZRUSENA" -> new ZrusenaStav();
            case "PROBEHLA" -> new ProbehlaStav();
            default -> new NepotvrzenaStav();
        };
    }
}