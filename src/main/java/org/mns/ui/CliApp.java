package org.mns.ui;

import org.mns.dao.*;
import org.mns.service.CustomerService;
import org.mns.service.ReservationService;
import org.mns.service.RestaurantService;
import org.mns.service.ReviewService;

import java.util.Scanner;

import static org.mns.ui.CliHelper.*;

/**
 * Hlavní třída CLI aplikace. Spravuje hlavní smyčku a přepínání menu.
 */
public class CliApp {
    private final Scanner sc = new Scanner(System.in);
    private final SessionContext session = new SessionContext();

    private final AuthHandler authHandler;
    private final ReservationHandler reservationHandler;
    private final ReviewHandler reviewHandler;

    public CliApp() {
        CustomerDao customerDao = new CustomerDaoImpl();
        TableDao tableDao = new TableDaoImpl();
        ReservationDao reservationDao = new ReservationDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        RestaurantDao restaurantDao = new RestaurantDaoImpl();

        CustomerService customerService = new CustomerService(customerDao);
        ReservationService reservationService = new ReservationService(reservationDao, tableDao);
        RestaurantService restaurantService = new RestaurantService(restaurantDao);
        ReviewService reviewService = new ReviewService(reviewDao, restaurantDao);

        this.authHandler = new AuthHandler(sc, session, customerService);
        this.reservationHandler = new ReservationHandler(sc, session, reservationService, restaurantService);
        this.reviewHandler = new ReviewHandler(sc, session, reviewService, restaurantService);
    }

    public void start() {
        System.out.println();
        System.out.println(BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + "║   Rezervační systém restaurací  v1.0 ║" + RESET);
        System.out.println(BOLD + "╚══════════════════════════════════════╝" + RESET);

        while (true) {
            try {
                if (session.isLoggedIn()) {
                    showCustomerMenu();
                } else {
                    showMainMenu();
                }
            } catch (Exception e) {
                error("Neočekávaná chyba: " + e.getMessage());
            }
        }
    }

    private void showMainMenu() {
        title("Hlavní menu");
        System.out.println("  1) Přihlásit se");
        System.out.println("  2) Registrovat se");
        System.out.println("  0) Ukončit aplikaci");
        nextLine();
        System.out.print("Volba: ");

        switch (readNumber(sc)) {
            case 1 -> authHandler.handleLogin();
            case 2 -> authHandler.handleRegistration();
            case 0 -> end();
            default -> error("Neplatná volba, zkuste znovu.");
        }
    }

    private void showCustomerMenu() {
        title("Vítej, " + session.getLoggedInUser().getFirstName() + " " + session.getLoggedInUser().getLastName());

        System.out.println("  1) Vyhledat restauraci");
        System.out.println("  2) Vytvořit rezervaci");
        System.out.println("  3) Moje rezervace");
        System.out.println("  4) Napsat recenzi");
        System.out.println("  5) Odhlásit se");
        System.out.println("  0) Ukončit aplikaci");
        nextLine();

        System.out.print("Volba: ");

        switch (readNumber(sc)) {
            case 1 -> reservationHandler.searchForRestaurants();
            case 2 -> reservationHandler.createReservation();
            case 3 -> reservationHandler.showMyReservations();
            case 4 -> reviewHandler.handleCreateReview();
            case 5 -> logout();
            case 0 -> end();
            default -> error("Neplatná volba, zkuste znovu.");
        }
    }

    private void logout() {
        String name = session.getLoggedInUser().getFirstName();
        session.logOut();
        success("Uživatel " + name + " byl odhlášen.");
    }

    private void end() {
        info("Ukončení aplikace. Na shledanou!");
        System.exit(0);
    }
}