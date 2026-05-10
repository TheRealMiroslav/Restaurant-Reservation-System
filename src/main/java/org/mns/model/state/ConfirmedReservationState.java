package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Stav reprezentující potvrzenou rezervaci.
 * Rezervaci lze v tomto stavu buď zrušit, nebo označit za proběhlou.
 */
public class ConfirmedReservationState implements ReservationState {
    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože rezervace je již potvrzena.
     */
    public void confirm(Reservation r) {
        throw new IllegalStateException("Rezervace je již potvrzena.");
    }

    /**
     * {@inheritDoc}
     * Změní stav na {@link CancelledReservationState}.
     */
    public void cancel(Reservation r) {
        r.setStatus(new CancelledReservationState());
    }

    /**
     * {@inheritDoc}
     * Změní stav na {@link PassedReservationState}.
     */
    public void complete(Reservation r) {
        r.setStatus(new PassedReservationState());
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "POTVRZENA";
    }
}