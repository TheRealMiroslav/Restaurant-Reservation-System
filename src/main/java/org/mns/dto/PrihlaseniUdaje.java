package org.mns.dto;

/**
 * DTO přenášející přihlašovací údaje z UI vrstvy do service vrstvy.
 * Důvod existence: service nepotřebuje celý objekt Zakaznik jen proto,
 * aby ověřila email + heslo.
 */
public class PrihlaseniUdaje {
    private final String email;
    private final String heslo;

    public PrihlaseniUdaje(String email, String heslo) {
        this.email = email;
        this.heslo = heslo;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }
}