package org.mns.facade;

import org.mns.model.Customer;

import java.sql.Timestamp;

public interface ReservationFacade {
    boolean bookTable(Customer customer, String restaurantName, int capacity, Timestamp from, Timestamp to, String comment) throws Exception;
}
