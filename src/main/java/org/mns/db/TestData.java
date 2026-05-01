package org.mns.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Naplní databázi testovacími daty při prvním spuštění.
 * Data se vloží pouze pokud jsou tabulky prázdné.
 */
public class TestData {

    public static void insert() {
        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement()) {

            // Spustí se jen pokud je DB prázdná
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM customer");
            rs.next();
            if (rs.getInt(1) > 0) return;

            System.out.println("Vkládám testovací data...");

            // Zákazníci
            stmt.execute("INSERT INTO customer (first_name, last_name, email, phone_number, password) VALUES " + "('Jan', 'Novk', 'jan@test.cz', '777111222', 'heslo123')," + "('Eva', 'Markov', 'eva@test.cz', '777333444', 'heslo123')");

            // Restaurace
            stmt.execute("INSERT INTO restaurant (name, address, phone_number, email, average_rating) VALUES " + "('U Zlatho jelena', 'Nm›st Republiky 5, Praha', '222333444', 'jelen@test.cz', 4.5)," + "('U Zeleneho jelena', 'Nm›st Republiky 5, Praha', '222333444', 'jelen@test.cz', 3.2)," + "('U modreho jelena', 'Nm›st Republiky 5, Praha', '222333444', 'jelen@test.cz', 5.0)," + "('U bezoveho jelena', 'Nm›st Republiky 5, Praha', '222333444', 'jelen@test.cz', 1.2)," + "('Pizzeria Roma', 'Karlova 12, Praha', '222555666', 'roma@test.cz', 3.8)," + "('Restaurace Na V›trnku', 'Plzeˆsk 88, Plzeˆ', '377123456', 'vetrnik@test.cz', 4.7)");

            // Stoly (restaurace 1)
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES " + "(1, 'A1', 2), (1, 'A2', 4), (1, 'B1', 6), (1, 'B2', 8)");

            // Stoly (restaurace 2)
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES " + "(2, 'S1', 2), (2, 'S2', 4), (2, 'S3', 4)");

            // Stoly (restaurace 3)
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES " + "(3, 'VIP', 10), (3, 'T1', 4), (3, 'T2', 4)");

            // Pár recenzí
            stmt.execute("INSERT INTO review (customer_id, restaurant_id, comment, rating) VALUES " + "(1, 1, 'Skv›l jdlo, doporuuji!', 5)," + "(2, 1, 'Dobr obsluha, ale trochu hluno.', 4)," + "(1, 3, 'Nejlep svkov v Plzni.', 5)");

            System.out.println("Testovací data vložena.");
            System.out.println("Testovací účty: jan@test.cz / heslo123 | eva@test.cz / heslo123");

        } catch (Exception e) {
            System.err.println("Chyba při vkládání testovacích dat: " + e.getMessage());
        }
    }
}