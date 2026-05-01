package org.mns.ui;

import org.mns.model.Reservation;
import org.mns.model.Restaurant;
import org.mns.model.Table;
import org.mns.service.ReservationService;
import org.mns.service.RestaurantService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import static org.mns.ui.CliHelper.*;
import static org.mns.ui.CliHelper.subtitle;

/**
 * Obsluhuje UC-02 Vyhledat restauraci, UC-03 Vytvořit rezervaci, UC-04 Zrušit rezervaci.
 */
public class ReservationHandler {
    private static final SimpleDateFormat FMT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private final Scanner sc;
    private final SessionContext session;
    private final ReservationService reservationService;
    private final RestaurantService restaurantService;

    public ReservationHandler(Scanner sc, SessionContext session, ReservationService reservationService, RestaurantService restaurantService) {
        this.sc = sc;
        this.session = session;
        this.reservationService = reservationService;
        this.restaurantService = restaurantService;
    }

    public void searchForRestaurants() {
        title("Vyhledání restaurace");

        System.out.print("Zadejte název nebo adresu: ");
        String searchedText = sc.nextLine().trim();

        if (searchedText.isBlank()) {
            error("Zadejte alespoň jeden znak pro vyhledávání.");
            return;
        }

        info("Vyhledávání restaurací...");

        try {
            List<Restaurant> restaurants = restaurantService.searchRestaurants(searchedText);

            if (restaurants.isEmpty()) {
                nextLine();
                info("Nebyly nalezeny žádné restaurace pro: \"" + searchedText + "\"");
                info("Zkuste upravit parametry vyhledávání.");
                return;
            }

            subtitle("Nalezené restaurace (" + restaurants.size() + ")");
            showRestaurants(restaurants);

        } catch (Exception e) {
            error("Chyba při vyhledávání: " + e.getMessage());
        }
    }

    public void createReservation() {
        title("Vytvoření rezervace");

        // Výběr restaurace
        System.out.print("Vyhledat restauraci (název/adresa): ");
        String searchedText = sc.nextLine().trim();

        info("Vyhledávání restaurací...");

        try {
            List<Restaurant> restaurant = restaurantService.searchRestaurants(searchedText);

            if (restaurant.isEmpty()) {
                nextLine();
                info("Žádná restaurace nenalezena.");
                return;
            }

            subtitle("Nalezené restaurace (" + restaurant.size() + ")");
            showRestaurants(restaurant);
            nextLine();

            System.out.print("Vyberte číslo restaurace: ");
            int selectedRestaurantIndex = readNumber(sc);

            if (selectedRestaurantIndex < 1 || selectedRestaurantIndex > restaurant.size()) {
                error("Neplatná volba.");
                return;
            }

            Restaurant selectedRestaurant = restaurant.get(selectedRestaurantIndex - 1);

            info("Načítání stolů...");

            // Výběr stolu
            List<Table> ListOfTables = restaurantService.getTables(selectedRestaurant.getId());

            subtitle("Dostupné stoly — " + selectedRestaurant.getName());

            if (ListOfTables.isEmpty()) {
                info("Tato restaurace nemá evidované žádné stoly.");
                return;
            }

            showTables(ListOfTables);

            System.out.print("Vyberte číslo stolu: ");
            int selectedTableIndex = readNumber(sc);

            if (selectedTableIndex < 1 || selectedTableIndex > ListOfTables.size()) {
                error("Neplatná volba.");
                return;
            }

            Table selectedTable = ListOfTables.get(selectedTableIndex - 1);

            // Formulář
            subtitle("Zadejte údaje rezervace");

            System.out.print("Datum a čas začátku (dd.MM.yyyy HH:mm): ");
            String fromStr = sc.nextLine().trim();

            System.out.print("Datum a čas konce  (dd.MM.yyyy HH:mm): ");
            String toStr = sc.nextLine().trim();

            System.out.print("Počet osob: ");
            int numOfPeople = readNumber(sc);

            System.out.print("Poznámka (Enter pro přeskočení): ");
            String comment = sc.nextLine().trim();

            Timestamp from;
            Timestamp to;
            try {
                from = new Timestamp(FMT.parse(fromStr).getTime());
                to = new Timestamp(FMT.parse(toStr).getTime());
            } catch (Exception e) {
                error("Neplatný formát data. Použijte: dd.MM.yyyy HH:mm");
                return;
            }

            if (numOfPeople < 1) {
                error("Počet osob musí být alespoň 1.");
                return;
            }

            // Krok 4: potvrzení
            subtitle("Shrnutí rezervace");
            System.out.println("  Restaurace : " + selectedRestaurant.getName());
            System.out.println("  Stůl       : " + selectedTable.getCode() + " (kapacita " + selectedTable.getCapacity() + " osob)");
            System.out.println("  Začátek    : " + fromStr);
            System.out.println("  Konec      : " + toStr);
            System.out.println("  Osob       : " + numOfPeople);
            if (!comment.isBlank()) System.out.println("  Poznámka   : " + comment);
            nextLine();

            System.out.print("Potvrdit rezervaci? (a/n): ");
            String confirmation = sc.nextLine().trim().toLowerCase();

            if (!confirmation.equals("a")) {
                info("Rezervace zrušena uživatelem.");
                return;
            }

            info("Zpracovávání rezervace...");
            nextLine();

            reservationService.createReservation(session.getLoggedInUser(), selectedTable, from, to, comment, numOfPeople);

            success("Rezervace byla úspěšně vytvořena!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            error(e.getMessage()); // kapacita překročena, stůl obsazen, rezervace v minulosti
        } catch (Exception e) {
            error("Chyba při vytváření rezervace: " + e.getMessage());
        }
    }

    public void showMyReservations() {
        info("Hledání rezervací...");

        try {
            List<Reservation> reservation = reservationService.getCustomerReservations(session.getLoggedInUser().getId());

            title("Moje rezervace");

            if (reservation.isEmpty()) {
                info("Nemáte žádné rezervace.");
                return;
            }

            showReservations(reservation);

            subtitle("Akce");
            System.out.println("  1) Potvrdit rezervaci");
            System.out.println("  2) Zrušit rezervaci");
            System.out.println("  0) Zpět");

            nextLine();
            System.out.print("Volba: ");

            switch (readNumber(sc)) {
                case 1 -> confirmReservation(reservation);
                case 2 -> cancelReservation(reservation);
                case 0 -> { /* zpět */ }
                default -> error("Neplatná volba.");
            }

        } catch (Exception e) {
            error("Chyba při načítání rezervací: " + e.getMessage());
        }
    }

    private void confirmReservation(List<Reservation> reservation) {
        System.out.print("Zadejte číslo rezervace k potvrzení: ");
        int selection = readNumber(sc);

        if (selection < 1 || selection > reservation.size()) {
            error("Neplatná volba.");
            return;
        }

        Reservation selected = reservation.get(selection - 1);

        System.out.print("Opravdu chcete potvrdit tuto rezervaci? (a/n): ");
        String confirmation = sc.nextLine().trim().toLowerCase();

        if (!confirmation.equals("a")) {
            info("Akce zrušena.");
            return;
        }

        info("Zpracovávání potvrzení rezervace");;
        nextLine();

        try {
            reservationService.confirmReservation(selected);

            success("Rezervace byla úspěšně potvrzena.");
        } catch (IllegalStateException e) {
            error(e.getMessage()); // State pattern vyhodí výjimku pro nepovolenou akci
        } catch (Exception e) {
            error("Chyba při potvrzování rezervace: " + e.getMessage());
        }
    }

    private void cancelReservation(List<Reservation> reservation) {
        System.out.print("Zadejte číslo rezervace ke zrušení: ");
        int selection = readNumber(sc);

        if (selection < 1 || selection > reservation.size()) {
            error("Neplatná volba.");
            return;
        }

        Reservation selected = reservation.get(selection - 1);

        System.out.print("Opravdu chcete zrušit tuto rezervaci? (a/n): ");
        String confirmation = sc.nextLine().trim().toLowerCase();

        if (!confirmation.equals("a")) {
            info("Akce zrušena.");
            return;
        }

        info("Zpracovávání zrušení rezervace");
        nextLine();

        try {
            reservationService.cancelReservation(selected);

            success("Rezervace byla úspěšně zrušena.");
        } catch (IllegalStateException e) {
            error(e.getMessage()); // State pattern vyhodí výjimku pro nepovolenou akci
        } catch (Exception e) {
            error("Chyba při rušení rezervace: " + e.getMessage());
        }
    }

    private void showRestaurants(List<Restaurant> restaurantList) {
        for (int i = 0; i < restaurantList.size(); i++) {
            Restaurant r = restaurantList.get(i);
            System.out.printf("  %d) %-30s %s%n", i + 1, r.getName(), toStars(r.getAverageRating()));
            System.out.printf("     %s%n", r.getAddress());
        }
    }

    private void showTables(List<Table> tableList) {
        for (int i = 0; i < tableList.size(); i++) {
            Table s = tableList.get(i);
            System.out.printf("  %d) Stůl %-8s — kapacita %d osob%n", i + 1, s.getCode(), s.getCapacity());
        }
        nextLine();
    }

    private void showReservations(List<Reservation> reservationList) {
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation r = reservationList.get(i);
            System.out.printf("  %d) Stůl ID: %-4d  %s → %s  [%s]%n", i + 1, r.getTableId(), FMT.format(r.getStartTime()), FMT.format(r.getEndTime()), r.getStatus().getName());
            if (r.getComment() != null && !r.getComment().isBlank())
                System.out.printf("          Poznámka: %s%n", r.getComment());
        }
    }
}
