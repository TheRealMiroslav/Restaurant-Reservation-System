package org.mns.factory;

import org.mns.model.state.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Továrna pro správu a získávání instancí stavů rezervace.
 * Využívá Flyweight pattern pro sdílení instancí bezstavových objektů {@link ReservationState}.
 */
public class ReservationStateFactory {
    private static final Map<String, ReservationState> states = new HashMap<>();

    private static final ReservationState DEFAULT_STATE = new UnconfirmedReservationState();

    static {
        registerState(new UnconfirmedReservationState());
        registerState(new ConfirmedReservationState());
        registerState(new CancelledReservationState());
        registerState(new PassedReservationState());
    }

    private static void registerState(ReservationState state) {
        states.put(state.getName(), state);
    }

    /**
     * Vrátí sdílenou instanci stavu na základě jeho jména z databáze.
     * Pokud stav neexistuje, vrátí výchozí (Unconfirmed).
     */
    public static ReservationState getState(String name) {
        return states.getOrDefault(name, DEFAULT_STATE);
    }
}
