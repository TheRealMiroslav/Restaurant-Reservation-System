package org.mns.dto;

/**
 * DTO přenášející přihlašovací údaje z UI vrstvy do service vrstvy.
 * Důvod existence: service nepotřebuje celý objekt Zakaznik jen proto,
 * aby ověřila email + heslo.
 */
public class LoginCredentials {
    private final String email;
    private final String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}