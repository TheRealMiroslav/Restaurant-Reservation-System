package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Zakaznik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ZakaznikDaoImpl implements ZakaznikDao {


    @Override
    public Optional<Zakaznik> najdiPodleEmailu(String email) throws Exception {
        String sql = "SELECT * FROM Zakaznik WHERE email LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            Optional<Zakaznik> zakaznik = getZakaznik(stmt);

            if (zakaznik.isPresent()) return zakaznik;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Zakaznik> najdiPodleId(int id) throws Exception {
        String sql = "SELECT * FROM Zakaznik WHERE id LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            Optional<Zakaznik> zakaznik = getZakaznik(stmt);

            if (zakaznik.isPresent()) return zakaznik;
        }

        return Optional.empty();
    }

    private Optional<Zakaznik> getZakaznik(PreparedStatement stmt) throws SQLException {
        var rs = stmt.executeQuery();

        if (rs.next()) {
            Zakaznik zakaznik = new Zakaznik();

            zakaznik.setId(rs.getInt("id"));
            zakaznik.setJmeno(rs.getString("jmeno"));
            zakaznik.setPrijmeni(rs.getString("prijmeni"));
            zakaznik.setEmail(rs.getString("email"));
            zakaznik.setTelefonniCislo(rs.getString("telefonniCislo"));
            zakaznik.setHeslo(rs.getString("heslo"));

            return Optional.of(zakaznik);
        }
        return Optional.empty();
    }

    @Override
    public void vytvorZakaznika(Zakaznik zakaznik) throws Exception {
        String sql = "INSERT INTO Zakaznik (id, jmeno, prijmeni, email, telefonniCislo, heslo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, zakaznik.getId());
            stmt.setString(2, zakaznik.getJmeno());
            stmt.setString(3, zakaznik.getPrijmeni());
            stmt.setString(4, zakaznik.getEmail());
            stmt.setString(5, zakaznik.getTelefonniCislo());
            stmt.setString(6, zakaznik.getHeslo());

            stmt.executeUpdate();
        }
    }
}