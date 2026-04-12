package org.mns;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:h2:./h2database;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void inicializujDatabazi() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS Zakaznik (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "jmeno VARCHAR(100), " + "prijmeni VARCHAR(100), " + "email VARCHAR(100) UNIQUE)");

            stmt.execute("CREATE TABLE IF NOT EXISTS Restaurace (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "nazev VARCHAR(100), " + "adresa VARCHAR(255), " + "telefon VARCHAR(20), " + "hodnoceni DOUBLE DEFAULT 0.0)");

            stmt.execute("CREATE TABLE IF NOT EXISTS Stul (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "restaurace_id INT, " + "kod_stolu VARCHAR(20), " + "kapacita INT, " + "FOREIGN KEY (restaurace_id) REFERENCES Restaurace(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Rezervace (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "zakaznik_id INT, " + "stul_id INT, " + "cas_zacatek TIMESTAMP, " + "cas_konec TIMESTAMP, " + "poznamky VARCHAR(255), " + "stavAktivni BOOLEAN DEFAULT FALSE, " + "FOREIGN KEY (zakaznik_id) REFERENCES Zakaznik(id), " + "FOREIGN KEY (stul_id) REFERENCES Stul(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Recenze (" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "zakaznik_id INT, " + "restaurace_id INT, " + "komentar VARCHAR(255), " + "hodnoceni INT, " + "FOREIGN KEY (zakaznik_id) REFERENCES Zakaznik(id), " + "FOREIGN KEY (restaurace_id) REFERENCES Restaurace(id))");

            System.out.println("Databáze je připravena!");

        } catch (Exception e) {
            System.err.println("Chyba při inicializaci DB: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void vyhledatRestauraci(String Text) {
        String sql = "SELECT FROM Restaurace WHERE navez LIKE ? OR adresa LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + Text + "%");
            pstmt.setString(2, "%" + Text + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Restaurace: " + rs.getString("nazev") + ", Adresa: " + rs.getString("adresa"));
            }
        } catch (Exception e) {
            System.err.println("Chyba při vyhledávání restaurace: " + e.getMessage());
        }
    }

    public void vytviritRezervaci(int zakaznikId, int stulId, Timestamp casZacatek, Timestamp casKonec, String poznamky) {
        String sql = "INSERT INTO Rezervace (zakaznik_id, stul_id, cas_zacatek, cas_konec, poznamky) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, zakaznikId);
            pstmt.setInt(2, stulId);
            pstmt.setTimestamp(3, casZacatek);
            pstmt.setTimestamp(4, casKonec);
            pstmt.setString(5, poznamky);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rezervace byla úspěšně vytvořena.");
            } else {
                System.out.println("Nepodařilo se vytvořit rezervaci.");
            }
        } catch (Exception e) {
            System.err.println("Chyba při vytváření rezervace: " + e.getMessage());
        }
    }
}