package org.mns.model.state;

import org.mns.model.Reservation;

public class CancelledReservationState implements ReservationState {
    public void confirm(Reservation r) {
        throw new IllegalStateException("Nelze confirm zrušenou rezervaci.");
    }

    public void cancel(Reservation r) {
        throw new IllegalStateException("Rezervace je již zrušena.");
    }

    public void complete(Reservation r) {
        throw new IllegalStateException("Nelze dokončit zrušenou rezervaci.");
    }

    public String getName() {
        return "ZRUSENA";
    }
}
