package org.mns.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:h2:./h2database;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void inicializujDatabazi() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS Zakaznik (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "jmeno VARCHAR(100), " + "prijmeni VARCHAR(100), " + "email VARCHAR(100) UNIQUE, " + "telefonniCislo VARCHAR(20), " + "heslo VARCHAR(255))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Restaurace (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "nazev VARCHAR(100), " + "adresa VARCHAR(255), " + "telefonniCislo VARCHAR(20), " + "email VARCHAR(100), " + "prumerneHodnoceni DOUBLE DEFAULT 0.0)");

            stmt.execute("CREATE TABLE IF NOT EXISTS Stul (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "restauraceId INT, " + "kodStolu VARCHAR(20), " + "kapacita INT, " + "FOREIGN KEY (restauraceId) REFERENCES Restaurace(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Rezervace (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "zakaznikId INT, " + "stulId INT, " + "casZacatek TIMESTAMP, " + "casKonec TIMESTAMP, " + "poznamky VARCHAR(255), " + "pocetOsob int, " + "stav VARCHAR(20) DEFAULT 'NEPOTVRZENA', " + "FOREIGN KEY (zakaznikId) REFERENCES Zakaznik(id), " + "FOREIGN KEY (stulId) REFERENCES Stul(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Recenze (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "zakaznikId INT, " + "restauraceId INT, " + "komentar VARCHAR(255), " + "hodnoceni DOUBLE, " + "FOREIGN KEY (zakaznikId) REFERENCES Zakaznik(id), " + "FOREIGN KEY (restauraceId) REFERENCES Restaurace(id))");

            System.out.println("Databáze je připravena!");

        } catch (Exception e) {
            System.err.println("Chyba při inicializaci DB: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}