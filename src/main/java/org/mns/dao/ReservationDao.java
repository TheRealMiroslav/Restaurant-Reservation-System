package org.mns.dao;

import org.mns.model.Reservation;

import java.util.List;

/**
 * Rozhraní pro správu rezervací v restauracích.
 */
public interface ReservationDao {
    /**
     * Uloží novou rezervaci do systému.
     *
     * @param reservation Objekt rezervace se všemi potřebnými údaji.
     * @throws Exception Při chybě ukládání.
     */
    void create(Reservation reservation) throws Exception;

    /**
     * Aktualizuje stav existující rezervace (např. na "DOKONČENÁ", "ZRUŠENÁ").
     *
     * @param reservation Rezervace s aktualizovaným stavem.
     * @throws Exception Při chybě v SQL dotazu.
     */
    void updateStatus(Reservation reservation) throws Exception;

    /**
     * Získá seznam všech rezervací pro konkrétního zákazníka.
     *
     * @param customerId ID zákazníka.
     * @return Seznam rezervací včetně detailů o stole a restauraci.
     * @throws Exception Při chybě načítání dat.
     */
    List<Reservation> getReservationByCustomerId(int customerId) throws Exception;

    /**
     * Ověří, zda má zákazník v dané restauraci již proběhlou rezervaci.
     * Slouží typicky k ověření nároku na napsání recenze.
     *
     * @param customerId   ID zákazníka.
     * @param restaurantId ID restaurace.
     * @return true, pokud byla nalezena aspoň jedna proběhlá rezervace.
     * @throws Exception Při chybě v DB.
     */
    boolean checkForReservation(int customerId, int restaurantId) throws Exception;
}