package org.mns.dto;

/**
 * DTO přenášející přihlašovací údaje z UI vrstvy do service vrstvy.
 * Důvod existence: service nepotřebuje celý objekt Zakaznik jen proto,
 * aby ověřila email + heslo.
 */
public record LoginCredentials(String email, String password) {
}