package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Interface návrhového vzoru State pro životní cyklus entity Rezervace.
 * Každý konkrétní stav definuje povolené přechody.
 */
public interface ReservationState {
    void confirm(Reservation r);

    void cancel(Reservation r);

    void complete(Reservation r);

    String getName();
}