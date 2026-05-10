package org.mns.service;

import org.mns.dto.LoginCredentials;
import org.mns.model.Customer;

import java.util.Optional;

/**
 * Služba pro správu uživatelských účtů (přihlašování a registrace).
 */
public interface CustomerService {
    /**
     * Provede přihlášení uživatele na základě e-mailu a hesla.
     *
     * @param credentials Přihlašovací údaje.
     * @return Optional s uživatelem, pokud jsou údaje správné, jinak prázdný Optional.
     * @throws Exception Při chybě databáze.
     */
    Optional<Customer> login(LoginCredentials credentials) throws Exception;

    /**
     * Zaregistruje nového zákazníka v systému.
     *
     * @param firstName   Jméno.
     * @param lastName    Příjmení.
     * @param email       E-mail (unikátní login).
     * @param phoneNumber Telefonní číslo.
     * @param password    Heslo v čitelné podobě (služba se postará o případné zpracování).
     * @throws Exception Při chybě databáze nebo pokud e-mail již existuje.
     */
    void register(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception;
}
