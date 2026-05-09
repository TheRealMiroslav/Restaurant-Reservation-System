package org.mns.model.state;

import org.mns.model.Reservation;

/**
 * Interface návrhového vzoru State pro životní cyklus entity Rezervace.
 * Každý konkrétní stav definuje povolené přechody a chování pro danou operaci.
 */
public interface ReservationState {
    /**
     * Provede potvrzení rezervace, pokud je to v daném stavu povoleno.
     *
     * @param r Rezervace, jejíž stav se má změnit.
     */
    void confirm(Reservation r);

    /**
     * Provede zrušení rezervace, pokud je to v daném stavu povoleno.
     *
     * @param r Rezervace, jejíž stav se má změnit.
     */
    void cancel(Reservation r);

    /**
     * Označí rezervaci jako proběhlou (dokončenou).
     *
     * @param r Rezervace, jejíž stav se má změnit.
     */
    void complete(Reservation r);

    /**
     * Vrátí textový identifikátor stavu (uložený v databázi).
     *
     * @return Název stavu.
     */
    String getName();
}