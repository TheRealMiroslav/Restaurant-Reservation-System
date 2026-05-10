package org.mns.facade;

import org.mns.model.Customer;

import java.sql.Timestamp;

/**
 * Fasáda pro zjednodušení procesu rezervace.
 * Sdružuje volání více služeb do jedné logické operace.
 */
public interface ReservationFacade {
    /**
     * Kompletní proces rezervace stolu na základě parametrů od klienta.
     * Vyhledá vhodnou restauraci, najde v ní volný stůl s dostatečnou kapacitou
     * a vytvoří rezervaci.
     *
     * @param customer       Zákazník provádějící rezervaci.
     * @param restaurantName Název nebo část adresy restaurace.
     * @param capacity       Požadovaný počet míst u stolu.
     * @param from           Čas začátku rezervace.
     * @param to             Čas konce rezervace.
     * @param comment        Poznámka k rezervaci.
     * @return true, pokud byla rezervace úspěšně vytvořena.
     * @throws Exception Pokud restaurace neexistuje, není volný žádný stůl, nebo při chybě DB.
     */
    boolean bookTable(Customer customer, String restaurantName, int capacity, Timestamp from, Timestamp to, String comment) throws Exception;
}
