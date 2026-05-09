package org.mns.dao;

import org.mns.model.Table;

import java.sql.Timestamp;

/**
 * Rozhraní pro operace se stoly v restauracích.
 */
public interface TableDao {
    /**
     * Ověří, zda je stůl volný v zadaném časovém intervalu.
     *
     * @param tableId   ID stolu.
     * @param startTime Začátek požadované rezervace.
     * @param endTime   Konec požadované rezervace.
     * @return true, pokud je stůl dostupný (není kolize), jinak false.
     * @throws Exception Při chybě komunikace s databází.
     */
    boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception;

    /**
     * Vyhledá stůl podle jeho ID.
     *
     * @param id ID stolu.
     * @return Objekt Table nebo null, pokud nebyl nalezen.
     * @throws Exception Při chybě komunikace s databází.
     */
    Table getById(int id) throws Exception;
}