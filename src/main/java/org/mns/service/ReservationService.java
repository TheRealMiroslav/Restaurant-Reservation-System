package org.mns.service;

import org.mns.model.Customer;
import org.mns.model.Reservation;
import org.mns.model.Table;

import java.sql.Timestamp;
import java.util.List;

/**
 * Služba pro správu rezervací stolů v restauracích.
 */
public interface ReservationService {
    /**
     * Vytvoří novou rezervaci po ověření dostupnosti stolu a jeho kapacity.
     *
     * @param customer    Zákazník, který si stůl rezervuje.
     * @param table       Stůl, který má být rezervován.
     * @param from        Datum a čas začátku rezervace.
     * @param to          Datum a čas konce rezervace.
     * @param comment     Volitelná poznámka k rezervaci.
     * @param numOfPeople Počet osob.
     * @throws Exception Pokud stůl není v daný čas volný nebo je překročena kapacita.
     */
    void createReservation(Customer customer, Table table, Timestamp from, Timestamp to, String comment, int numOfPeople) throws Exception;

    /**
     * Zruší existující rezervaci a aktualizuje její stav v databázi.
     *
     * @param reservation Rezervace k zrušení.
     * @throws Exception Při chybě komunikace s databází.
     */
    void cancelReservation(Reservation reservation) throws Exception;

    /**
     * Potvrdí rezervaci (změní stav na potvrzený/proběhlý dle logiky).
     *
     * @param reservation Rezervace k potvrzení.
     * @throws Exception Při chybě komunikace s databází.
     */
    void confirmReservation(Reservation reservation) throws Exception;

    /**
     * Vrátí seznam všech rezervací pro daného zákazníka.
     *
     * @param customerId ID zákazníka.
     * @return Seznam rezervací.
     * @throws Exception Při chybě komunikace s databází.
     */
    List<Reservation> getCustomerReservations(int customerId) throws Exception;

    /**
     * Vyhledá stůl podle jeho ID.
     *
     * @param tableId ID stolu.
     * @return Objekt Table.
     * @throws Exception Pokud stůl není nalezen nebo dojde k chybě DB.
     */
    Table getTableById(int tableId) throws Exception;

    /**
     * Ověří, zda je stůl volný v zadaném časovém intervalu.
     *
     * @param tableId   ID stolu.
     * @param from      Začátek požadované rezervace.
     * @param to        Konec požadované rezervace.
     * @return true, pokud je stůl dostupný (není kolize), jinak false.
     * @throws Exception Při chybě komunikace s databází.
     */
    boolean isTableAvailable(int tableId, Timestamp from, Timestamp to) throws Exception;
}
