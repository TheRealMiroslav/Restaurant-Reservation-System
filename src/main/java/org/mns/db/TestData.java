package org.mns.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Třída zajišťující prvotní naplnění databáze (data seeding) výchozími testovacími daty.
 * <p>
 * Slouží k rychlému nastavení konzistentního stavu aplikace pro účely vývoje,
 * ladění a demonstrace funkcionality.
 */
public class TestData {

    /**
     * Provede vložení testovacích záznamů do všech klíčových tabulek databáze.
     * <p>
     * Metoda je idempotentní vzhledem k obsahu tabulky {@code customer}. Nejprve
     * provede kontrolu existence dat; pokud tabulka zákazníků již obsahuje záznamy,
     * operace se přeruší, aby se zabránilo vzniku duplicitních dat.
     * * Vkládaná data zahrnují:
     * <ul>
     * <li>Ukázkové profily zákazníků</li>
     * <li>Seznam restaurací s různým hodnocením</li>
     * <li>Definice stolů a jejich kapacit pro jednotlivé restaurace</li>
     * <li>Historii rezervací (včetně proběhlých i zrušených)</li>
     * <li>Uživatelské recenze a komentáře</li>
     * </ul>
     */
    public static void insert() {
        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement()) {

            // Kontrola, zda databáze již obsahuje data (prevence duplicit)
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM customer");
            rs.next();
            if (rs.getInt(1) > 0) return;

            System.out.println("Vkládám testovací data...");

            // 1. Zákazníci - vložení výchozích uživatelských účtů
            stmt.execute("INSERT INTO customer (first_name, last_name, email, phone_number, password) VALUES "
                    + "('Jan', 'Novák', 'jan@test.cz', '777111222', '1234'),"
                    + "('Eva', 'Marková', 'eva@test.cz', '777333444', '1234')");

            // 2. Restaurace - různé typy podniků pro testování filtrů a vyhledávání
            stmt.execute("INSERT INTO restaurant (name, address, phone_number, email, average_rating) VALUES "
                    + "('U Zlatého jelena', 'Náměstí Republiky 5, Praha', '222333444', 'jelen@test.cz', 4.5),"
                    + "('U Zeleného jelena', 'Náměstí Republiky 5, Praha', '222333444', 'jelen@test.cz', 3.2),"
                    + "('U modrého jelena', 'Náměstí Republiky 5, Praha', '222333444', 'jelen@test.cz', 5.0),"
                    + "('U béžového jelena', 'Náměstí Republiky 5, Praha', '222333444', 'jelen@test.cz', 1.2),"
                    + "('Pizzeria Roma', 'Karlova 12, Praha', '222555666', 'roma@test.cz', 3.8),"
                    + "('Restaurace Na Větrníku', 'Plzeňská 88, Plzeň', '377123456', 'vetrnik@test.cz', 4.7)");

            // 3. Stoly - definice rozvržení stolů s různou kapacitou (Přiřazení ID odpovídá pořadí vložení restaurací)
            // Restaurace 1 (ID 1): Stoly 1-4
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES "
                    + "(1, 'A1', 2), (1, 'A2', 4), (1, 'B1', 6), (1, 'B2', 8)");
            // Restaurace 2 (ID 2): Stoly 5-7
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES "
                    + "(2, 'S1', 2), (2, 'S2', 4), (2, 'S3', 4)");
            // Restaurace 3 (ID 3): Stoly 8-10
            stmt.execute("INSERT INTO restaurant_table (restaurant_id, table_code, capacity) VALUES "
                    + "(3, 'VIP', 10), (3, 'T1', 4), (3, 'T2', 4)");

            // 4. Rezervace - testovací historie pro ověření stavů a výpisu rezervací uživatele
            stmt.execute("INSERT INTO reservation (customer_id, table_id, start_time, end_time, person_count, status, notes) VALUES "
                    + "(1, 1, '2024-01-10 18:00:00', '2024-01-10 20:00:00', 2, 'PROBEHLA', 'Rande, vše v pořádku'),"
                    + "(2, 5, '2024-02-15 12:30:00', '2024-02-15 14:00:00', 4, 'PROBEHLA', 'Pracovní oběd'),"
                    + "(1, 8, '2024-03-01 19:00:00', '2024-03-01 22:00:00', 2, 'PROBEHLA', 'Oslava narozenin'),"
                    + "(1, 2, '2024-04-20 17:00:00', '2024-04-20 19:00:00', 3, 'ZRUSENA', 'Zákazník nedorazil')");

            // 5. Stávající recenze - zpětná vazba od uživatelů pro výpočet průměrných hodnocení
            stmt.execute("INSERT INTO review (customer_id, restaurant_id, comment, rating) VALUES "
                    + "(1, 1, 'Skvělé jídlo, doporučuji!', 5),"
                    + "(2, 1, 'Dobrá obsluha, ale trochu hlučno.', 4),"
                    + "(1, 3, 'Nejlepší svíčková v Plzni.', 5)");

            System.out.println("Testovací data vložena.\n");

        } catch (Exception e) {
            System.err.println("Chyba při vkládání testovacích dat: " + e.getMessage());
        }
    }
}