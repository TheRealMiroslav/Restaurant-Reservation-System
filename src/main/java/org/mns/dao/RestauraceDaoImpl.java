package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Restaurace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestauraceDaoImpl implements RestauraceDao {

    @Override
    public Optional<Restaurace> getRestauraceById(int id) throws Exception {
        String sql = "SELECT * FROM Restaurace WHERE id LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                Restaurace restaurace = getRestaurace(rs);
                return Optional.of(restaurace);
            }

            return Optional.empty();
        }
    }

    @Override
    public List<Restaurace> getVsechnyRestaurace() throws Exception {
        List<Restaurace> restauraceList = new ArrayList<>();

        String sql = "SELECT * FROM Restaurace";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();

            while (rs.next()) {
                restauraceList.add(getRestaurace(rs));
            }

            return restauraceList;
        }
    }

    @Override
    public List<Restaurace> vyhledatRestauraciPodleTextu(String Text) throws Exception {
        List<Restaurace> restauraceList = new ArrayList<>();

        String sql = "SELECT * FROM Restaurace WHERE nazev LIKE ? OR adresa LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();

            while (rs.next()) {
                restauraceList.add(getRestaurace(rs));
            }

            return restauraceList;
        }
    }

    private Restaurace getRestaurace(ResultSet rs) throws SQLException {
        Restaurace restaurace = new Restaurace();

        restaurace.setId(rs.getInt("id"));
        restaurace.setNazev(rs.getString("nazev"));
        restaurace.setAdresa(rs.getString("adresa"));
        restaurace.setTelefonniCislo(rs.getString("telefonniCislo"));
        restaurace.setPrumerneHodnoceni(rs.getDouble("prumerneHodnoceni"));

        return restaurace;
    }
}