package org.mns.model.state;

import org.mns.model.Reservation;

public class ConfirmedReservationState implements ReservationState {
    public void confirm(Reservation r) {
        throw new IllegalStateException("Rezervace je již potvrzena.");
    }

    public void cancel(Reservation r) {
        r.setStatus(new CancelledReservationState());
    }

    public void complete(Reservation r) {
        r.setStatus(new PassedReservationState());
    }

    public String getName() {
        return "POTVRZENA";
    }
}