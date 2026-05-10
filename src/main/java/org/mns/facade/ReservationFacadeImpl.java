package org.mns.facade;

import org.mns.model.Customer;
import org.mns.model.Restaurant;
import org.mns.model.Table;
import org.mns.service.ReservationService;
import org.mns.service.RestaurantService;

import java.sql.Timestamp;
import java.util.List;

import static org.mns.ui.CliHelper.nextLine;

/**
 * Implementace fasády pro rezervace.
 * Koordinuje {@link RestaurantService} a {@link ReservationService}.
 */
public class ReservationFacadeImpl implements ReservationFacade {
    private final RestaurantService restaurantService;
    private final ReservationService reservationService;

    public ReservationFacadeImpl(RestaurantService restaurantService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.reservationService = reservationService;
    }

    /**
     * Fasádní metoda pro kompletní vyřízení rezervace (najde restauraci, vybere volný stůl a zarezervuje).
     */
    public boolean bookTable(Customer customer, String restaurantName, int capacity, Timestamp from, Timestamp to, String comment) throws Exception {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(restaurantName);

        if (restaurants.isEmpty()) {
            throw new IllegalArgumentException("Restaurace s názvem/adresou '" + restaurantName + "' nebyla nalezena.");
        }

        Restaurant selectedRestaurant = restaurants.getFirst();

        List<Table> tables = restaurantService.getTables(selectedRestaurant.getId());

        for (Table table : tables) {
            if (table.getCapacity() >= capacity) {
                try {
                    reservationService.createReservation(customer, table, from, to, comment, capacity);
                    return true; // Rezervace úspěšná!
                } catch (IllegalArgumentException e) {
                    // Tento stůl není volný, zkusíme další}
                }
            }
        }

        throw new IllegalStateException("V zadaném čase není v restauraci " + selectedRestaurant.getName() + " volný žádný stůl s požadovanou kapacitou.");
    }
}