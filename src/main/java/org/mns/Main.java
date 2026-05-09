package org.mns;

import org.mns.db.DatabaseManager;
import org.mns.db.TestData;
import org.mns.ui.CliApp;

/**
 * Vstupní bod aplikace.
 * Inicializuje databázi, vloží testovací data a spustí uživatelské rozhraní.
 */
public class Main {
    /**
     * Hlavní metoda aplikace.
     *
     * @param args Argumenty příkazové řádky (nepoužity).
     */
    public static void main(String[] args) {
        DatabaseManager.initialize();
        TestData.insert();

        System.out.println("Testovací účty: jan@test.cz / 1234 | eva@test.cz / 1234");

        new CliApp().start();
    }
}