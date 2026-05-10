package org.mns.dao;

import org.mns.model.Customer;

import java.util.Optional;

/**
 * Rozhraní pro přístup k datům zákazníků (Customer).
 */
public interface CustomerDao {
    /**
     * Vyhledá zákazníka podle e-mailové adresy.
     *
     * @param email E-mail zákazníka.
     * @return Optional obsahující zákazníka, nebo prázdný Optional, pokud nebyl nalezen.
     * @throws Exception Při chybě komunikace s databází.
     */
    Optional<Customer> getByEmail(String email) throws Exception;

    /**
     * Vytvoří nového zákazníka v systému.
     *
     * @param firstName   Jméno
     * @param lastName    Příjmení
     * @param email       E-mail (slouží jako login)
     * @param phoneNumber Telefonní číslo
     * @param password    Heslo (očekává se již zašifrované nebo v surovém stavu dle logiky aplikace)
     * @throws Exception Při chybě při zápisu do databáze.
     */
    void create(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception;
}