package org.mns.ui;

import org.mns.dao.*;
import org.mns.observer.RestaurantRatingUpdater;
import org.mns.observer.ReviewObserver;
import org.mns.service.CustomerService;
import org.mns.service.ReservationService;
import org.mns.service.RestaurantService;
import org.mns.service.ReviewService;
import org.mns.ui.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mns.ui.CliHelper.*;

/**
 * Hlavní třída CLI aplikace. Spravuje hlavní smyčku a přepínání menu pomocí Command patternu.
 */
public class CliApp {
    private final Scanner sc = new Scanner(System.in);
    private final SessionContext session = new SessionContext();

    private final AuthHandler authHandler;
    private final ReservationHandler reservationHandler;
    private final ReviewHandler reviewHandler;

    // Mapy pro Command pattern
    private final Map<Integer, Command> mainMenuCommands = new HashMap<>();
    private final Map<Integer, Command> customerMenuCommands = new HashMap<>();

    public CliApp() {
        CustomerDao customerDao = new CustomerDaoImpl();
        TableDao tableDao = new TableDaoImpl();
        ReservationDao reservationDao = new ReservationDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        RestaurantDao restaurantDao = new RestaurantDaoImpl();

        CustomerService customerService = new CustomerService(customerDao);
        ReservationService reservationService = new ReservationService(reservationDao, tableDao);
        RestaurantService restaurantService = new RestaurantService(restaurantDao);
        ReviewService reviewService = new ReviewService(reviewDao, reservationDao);

        ReviewObserver ratingUpdater = new RestaurantRatingUpdater(restaurantDao);
        reviewService.addObserver(ratingUpdater);

        this.authHandler = new AuthHandler(sc, session, customerService);
        this.reservationHandler = new ReservationHandler(sc, session, reservationService, restaurantService);
        this.reviewHandler = new ReviewHandler(sc, session, reviewService, restaurantService);

        // Zaregistrování příkazů do map
        initCommands();
    }

    private void initCommands() {
        mainMenuCommands.put(1, authHandler::handleLogin);
        mainMenuCommands.put(2, authHandler::handleRegistration);
        mainMenuCommands.put(0, this::end);

        customerMenuCommands.put(1, reservationHandler::searchForRestaurants);
        customerMenuCommands.put(2, reservationHandler::createReservation);
        customerMenuCommands.put(3, reservationHandler::showMyReservations);
        customerMenuCommands.put(4, reviewHandler::handleCreateReview);
        customerMenuCommands.put(5, reviewHandler::showMyReviews);
        customerMenuCommands.put(6, this::logout);
        customerMenuCommands.put(0, this::end);
    }

    public void start() {
        System.out.println();
        System.out.println(BOLD + "╔════════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + "║   Rezervační systém restaurací  v1.0   ║" + RESET);
        System.out.println(BOLD + "╚════════════════════════════════════════╝" + RESET);

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

        executeCommand(mainMenuCommands, readNumber(sc));
    }

    private void showCustomerMenu() {
        title("Vítej, " + session.getLoggedInUser().getFirstName() + " " + session.getLoggedInUser().getLastName());

        System.out.println("  1) Vyhledat restauraci");
        System.out.println("  2) Vytvořit rezervaci");
        System.out.println("  3) Moje rezervace");
        System.out.println("  4) Napsat recenzi");
        System.out.println("  5) Moje recenze");
        System.out.println("  6) Odhlásit se");
        System.out.println("  0) Ukončit aplikaci");
        nextLine();

        System.out.print("Volba: ");

        executeCommand(customerMenuCommands, readNumber(sc));
    }

    /**
     * Bezpečné spuštění příkazu z poskytnuté mapy na základě uživatelské volby.
     */
    private void executeCommand(Map<Integer, Command> menu, int choice) {
        Command command = menu.get(choice);
        if (command != null) {
            command.execute();
        } else {
            error("Neplatná volba, zkuste znovu.");
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