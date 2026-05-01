package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Reservation;
import org.mns.model.state.*;

import java.sql.Connection;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public void create(Reservation reservation) throws Exception {
        String sql = "INSERT INTO Rezervace (zakaznikId, stulId, casZacatek, casKonec, poznamky, pocetOsob, stav) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getCustomerId());
            stmt.setInt(2, reservation.getTableId());
            stmt.setTimestamp(3, reservation.getStartTime());
            stmt.setTimestamp(4, reservation.getEndTime());
            stmt.setString(5, reservation.getComment());
            stmt.setInt(6, reservation.getNumOfPeople());
            stmt.setString(7, reservation.getStatus().getName());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rezervace byla úspěšně vytvořena.");
            } else {
                System.out.println("Nepodařilo se vytvořit rezervaci.");
            }
        }
    }

    @Override
    public void cancel(int id) throws Exception {
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
    public List<Reservation> getReservationByCustomerId(int customerId) throws Exception {
        List<Reservation> reservationList = new java.util.ArrayList<>();

        String sql = "SELECT * FROM Rezervace WHERE zakaznikId = ?";

        try (Connection conn = DatabaseManager.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();

                reservation.setId(rs.getInt("id"));
                reservation.setCustomerId(rs.getInt("zakaznikId"));
                reservation.setTableId(rs.getInt("tableId"));
                reservation.setStartTime(rs.getTimestamp("casZacatek"));
                reservation.setEndTime(rs.getTimestamp("casKonec"));
                reservation.setComment(rs.getString("poznamky"));
                reservation.setNumOfPeople(rs.getInt("pocetOsob"));
                reservation.setStatus(getStatus(rs.getString("stav")));

                reservationList.add(reservation);
            }
        }

        return reservationList;
    }

    private ReservationState getStatus(String status) {
        return switch (status) {
            case "POTVRZENA" -> new ConfirmedReservationState();
            case "ZRUSENA" -> new CancelledReservationState();
            case "PROBEHLA" -> new PassedReservationState();
            default -> new UnconfirmedReservationState();
        };
    }
}