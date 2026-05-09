package org.mns.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * Správce databázového spojení a inicializace schématu.
 * Zajišťuje připojení k H2 databázi a vytvoření tabulek při startu aplikace.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:h2:./h2database;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    /**
     * Inicializuje databázi spuštěním SQL skriptu schema.sql.
     * Skript vytvoří potřebné tabulky, pokud ještě neexistují.
     *
     * @throws RuntimeException Pokud nelze najít schema.sql nebo inicializace selže.
     */
    public static void initialize() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            InputStream is = DatabaseManager.class.getClassLoader().getResourceAsStream("schema.sql");
            if (is == null) throw new RuntimeException("Soubor schema.sql nebyl nalezen!");

            String sql = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

            stmt.execute(sql);
            System.out.println("Databázové schéma bylo úspěšně inicializováno.");

        } catch (Exception e) {
            throw new RuntimeException("Chyba při inicializaci DB: " + e.getMessage(), e);
        }
    }

    /**
     * Vytvoří a vrátí nové spojení s databází.
     *
     * @return Connection objekt pro komunikaci s DB.
     * @throws Exception Při chybě navazování spojení.
     */
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}