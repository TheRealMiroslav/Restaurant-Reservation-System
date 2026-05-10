package org.mns.model.state;

import org.mns.model.Reservation;

public class PassedReservationState implements ReservationState {
    public void confirm(Reservation r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze confirm.");
    }

    public void cancel(Reservation r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze zrušit.");
    }

    public void complete(Reservation r) {
        throw new IllegalStateException("Rezervace již proběhla.");
    }

    public String getName() {
        return "PROBEHLA";
    }
}
