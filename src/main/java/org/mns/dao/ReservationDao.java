package org.mns.dao;

import org.mns.model.Reservation;

import java.util.List;

public interface ReservationDao {
    void create(Reservation reservation) throws Exception;

    // void edit(int reservationId, Reservation reservation);

    void updateStatus(Reservation reservation) throws Exception;

    List<Reservation> getReservationByCustomerId(int customerId) throws Exception;

    boolean checkForReservation(int customerId, int restaurantId) throws Exception;
}