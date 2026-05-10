package org.mns.ui;

import org.mns.model.Customer;

/**
 * Kontext aktuální relace uživatele.
 * Uchovává informaci o právě přihlášeném zákazníkovi.
 */
public class SessionContext {
    private Customer loggedInUser;

    /**
     * Zjistí, zda je v aktuální relaci přihlášen uživatel.
     *
     * @return true, pokud je uživatel přihlášen.
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Vrátí aktuálně přihlášeného uživatele.
     *
     * @return Objekt přihlášeného uživatele nebo null.
     */
    public Customer getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Nastaví přihlášeného uživatele do kontextu relace.
     *
     * @param customer Objekt přihlášeného zákazníka.
     */
    public void setLoggedInUser(Customer customer) {
        this.loggedInUser = customer;
    }

    /**
     * Zruší aktuální relaci (odhlásí uživatele).
     */
    public void logOut() {
        this.loggedInUser = null;
    }
}
