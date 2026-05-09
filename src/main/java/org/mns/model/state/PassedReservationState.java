package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Stav reprezentující rezervaci, která již úspěšně proběhla.
 * Jde o koncový stav životního cyklu.
 */
public class PassedReservationState implements ReservationState {
    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože proběhlou rezervaci již nelze měnit.
     */
    public void confirm(Reservation r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze confirm.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože proběhlou rezervaci nelze zrušit.
     */
    public void cancel(Reservation r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze zrušit.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože rezervace již proběhla.
     */
    public void complete(Reservation r) {
        throw new IllegalStateException("Rezervace již proběhla.");
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "PROBEHLA";
    }
}
