package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Reprezentuje stav rezervace, která byla vytvořena, ale zatím nebyla potvrzena personálem.
 */
public class UnconfirmedReservationState implements ReservationState {
    /**
     * {@inheritDoc}
     * Změní stav rezervace na potvrzenou.
     */
    public void confirm(Reservation r) {
        r.setStatus(new ConfirmedReservationState());
    }

    /**
     * {@inheritDoc}
     * Změní stav rezervace na zrušenou.
     */
    public void cancel(Reservation r) {
        r.setStatus(new CancelledReservationState());
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Nelze dokončit rezervaci, která nebyla potvrzena.
     */
    public void complete(Reservation r) {
        throw new IllegalStateException("Nelze dokončit nepotvrzenou rezervaci.");
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "NEPOTVRZENA";
    }
}
