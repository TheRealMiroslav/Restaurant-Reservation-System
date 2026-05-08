package org.mns.ui;

import org.mns.model.Restaurant;
import org.mns.model.Review;
import org.mns.service.RestaurantService;
import org.mns.service.ReviewService;

import java.util.List;
import java.util.Scanner;

import static org.mns.ui.CliHelper.*;

public class ReviewHandler {
    private final Scanner sc;
    private final SessionContext session;
    private final ReviewService reviewService;
    private final RestaurantService restaurantService;

    public ReviewHandler(Scanner sc, SessionContext session, ReviewService reviewService, RestaurantService restaurantService) {
        this.sc = sc;
        this.session = session;
        this.reviewService = reviewService;
        this.restaurantService = restaurantService;
    }

    /**
     * Obsluhuje UC-05 Napsat recenzi
     */
    public void handleCreateReview() {
        title("Napsat recenzi");

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

            for (int i = 0; i < restaurant.size(); i++) {
                Restaurant rest = restaurant.get(i);
                System.out.printf("  %d) %-30s %s%n", i + 1, rest.getName(), toStars(rest.getAverageRating()));
            }
            nextLine();

            System.out.print("Vyberte číslo restaurace: ");
            int selectedRestaurantIndex = readNumber(sc);

            if (selectedRestaurantIndex < 1 || selectedRestaurantIndex > restaurant.size()) {
                error("Neplatná volba.");
                return;
            }

            Restaurant selectedRestaurant = restaurant.get(selectedRestaurantIndex - 1);

            // Hodnocení
            subtitle("Hodnocení restaurace: " + selectedRestaurant.getName());
            System.out.println("  Zadejte hodnocení od 1 (nejhorší) do 5 (nejlepší):");
            System.out.print("  Hodnocení: ");
            int rating = readNumber(sc);

            if (rating < 1 || rating > 5) {
                error("Hodnocení musí být číslo od 1 do 5.");
                return;
            }

            // Komentář (volitelný)
            System.out.print("  Komentář (Enter pro přeskočení): ");
            String comment = sc.nextLine().trim();

            // Potvrzení
            subtitle("Shrnutí recenze");
            System.out.println("  Restaurace : " + selectedRestaurant.getName());
            System.out.println("  Hodnocení  : " + toStars(rating));

            if (!comment.isBlank()) {
                System.out.println("  Komentář   : " + comment);
            }

            nextLine();

            System.out.print("Odeslat recenzi? (a/n): ");
            String confirmation = sc.nextLine().trim().toLowerCase();

            if (!confirmation.equals("a")) {
                info("Recenze zrušena uživatelem.");
                return;
            }

            info("Zpracovávání recenze...");
            nextLine();

            reviewService.createReview(session.getLoggedInUser().getId(), selectedRestaurant.getId(), rating, comment);

            success("Recenze byla úspěšně odeslána. Děkujeme za hodnocení!");

        } catch (IllegalArgumentException e) {
            error(e.getMessage()); // nemá proběhlou rezervaci (UC-05 výjimka E1)
        } catch (Exception e) {
            error("Chyba při odesílání recenze: " + e.getMessage());
        }
    }

    public void showMyReviews() {
        info("Hledání recenzí...");

        try {
            List<Review> reviews = reviewService.getCustomerReviews(session.getLoggedInUser().getId());

            title("Moje recenze");

            if (reviews.isEmpty()) {
                info("Nemáte žádné recenze.");
                return;
            }

            showReviews(reviews);

            subtitle("Akce");
            System.out.println("  0) Zpět");

            nextLine();
            System.out.print("Volba: ");

            if (readNumber(sc) != 0) {
                error("Neplatná volba.");
            }

        } catch (Exception e) {
            error("Chyba při načítání rezervací: " + e.getMessage());
        }
    }

    private void showReviews(List<org.mns.model.Review> reviewList) {
        for (int i = 0; i < reviewList.size(); i++) {
            org.mns.model.Review r = reviewList.get(i);
            System.out.printf("  %d) Hodnocení: %s%n", i + 1, toStars(r.getRating()));
            if (r.getComment() != null && !r.getComment().isBlank()) {
                System.out.printf("     Komentář: %s%n", r.getComment());
            }
        }
    }
}
