package org.mns.service;

import org.mns.model.Customer;
import org.mns.model.Reservation;
import org.mns.model.Table;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationService {
    void createReservation(Customer customer, Table table, Timestamp from, Timestamp to, String comment, int numOfPeople) throws Exception;

    void cancelReservation(Reservation reservation) throws Exception;

    void confirmReservation(Reservation reservation) throws Exception;

    List<Reservation> getCustomerReservations(int customerId) throws Exception;

    Table getTableById(int tableId) throws Exception;
}
