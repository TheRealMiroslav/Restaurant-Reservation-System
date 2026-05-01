package org.mns.model.state;

import org.mns.model.Reservation;

public class UnconfirmedReservationState implements ReservationState {
    public void confirm(Reservation r) {
        r.setStatus(new ConfirmedReservationState());
    }

    public void cancel(Reservation r) {
        r.setStatus(new CancelledReservationState());
    }

    public void complete(Reservation r) {
        throw new IllegalStateException("Nelze dokončit nepotvrzenou rezervaci.");
    }

    public String getName() {
        return "NEPOTVRZENA";
    }
}
