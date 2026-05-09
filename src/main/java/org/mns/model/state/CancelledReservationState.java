package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Stav reprezentující zrušenou rezervaci.
 * V tomto stavu již nejsou povoleny žádné další akce.
 */
public class CancelledReservationState implements ReservationState {
    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože zrušenou rezervaci nelze potvrdit.
     */
    public void confirm(Reservation r) {
        throw new IllegalStateException("Nelze confirm zrušenou rezervaci.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože rezervace je již zrušena.
     */
    public void cancel(Reservation r) {
        throw new IllegalStateException("Rezervace je již zrušena.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException Vždy, protože zrušenou rezervaci nelze dokončit.
     */
    public void complete(Reservation r) {
        throw new IllegalStateException("Nelze dokončit zrušenou rezervaci.");
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "ZRUSENA";
    }
}
