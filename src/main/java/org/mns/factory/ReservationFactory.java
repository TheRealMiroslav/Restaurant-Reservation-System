package org.mns.factory;

import org.mns.model.Reservation;
import org.mns.model.Table;
import org.mns.model.Customer;

import java.sql.Timestamp;

;

public class ReservationFactory {
    public static Reservation createNewReservation(Customer customer, Table table, Timestamp from, Timestamp to, String comment, int numOfPeople) {
        // Validace na jednom místě
        if (numOfPeople > table.getCapacity()) {
            throw new IllegalArgumentException("Počet osob přesahuje kapacitu stolu");
        }

        if (from.after(to)) {
            throw new IllegalArgumentException("Začátek je po konci");
        }

        if (from.before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Nelze rezervovat v minulosti");
        }

        return new Reservation(customer.getId(), table.getId(), from, to, comment, numOfPeople);
    }
}