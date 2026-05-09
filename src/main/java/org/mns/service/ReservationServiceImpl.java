package org.mns.service;

import org.mns.builder.ReservationBuilder;
import org.mns.dao.ReservationDao;
import org.mns.dao.TableDao;
import org.mns.model.Customer;
import org.mns.model.Reservation;
import org.mns.model.Table;

import java.sql.Timestamp;
import java.util.List;

/**
 * Implementace služby pro správu rezervací.
 * Využívá {@link ReservationBuilder} pro vytváření instancí rezervací.
 */
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final TableDao tableDao;

    public ReservationServiceImpl(ReservationDao reservationDao, TableDao tableDao) {
        this.reservationDao = reservationDao;
        this.tableDao = tableDao;
    }

    /**
     * {@inheritDoc}
     */
    // UC-03
    public void createReservation(Customer customer, Table table, Timestamp from, Timestamp to, String comment, int numOfPeople) throws Exception {
        // UC-03 krok 7 - kontrola kolize
        boolean available = tableDao.isTableAvailable(table.getId(), from, to);

        if (!available) {
            throw new IllegalArgumentException("Stůl není dostupný v daném časovém období");
        }

        if (numOfPeople > table.getCapacity()) {
            throw new IllegalArgumentException("Počet osob přesahuje kapacitu stolu.");
        }

        Reservation reservation = new ReservationBuilder().setCustomer(customer.getId()).setTable(table.getId()).setStartTime(from).setEndTime(to).setComment(comment).setNumOfPeople(numOfPeople).build();

        reservationDao.create(reservation);
    }

    /**
     * {@inheritDoc}
     */
    // UC-04
    public void cancelReservation(Reservation reservation) throws Exception {
        reservation.cancel();
        reservationDao.updateStatus(reservation);
    }

    /**
     * {@inheritDoc}
     */
    public void confirmReservation(Reservation reservation) throws Exception {
        reservation.confirm();
        reservationDao.updateStatus(reservation);
    }

    /**
     * {@inheritDoc}
     */
    public List<Reservation> getCustomerReservations(int customerId) throws Exception {
        return reservationDao.getReservationByCustomerId(customerId);
    }

    /**
     * {@inheritDoc}
     */
    public Table getTableById(int tableId) throws Exception {
        return tableDao.getById(tableId);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTableAvailable(int tableId, Timestamp from, Timestamp to) throws Exception {
        return tableDao.isTableAvailable(tableId, from, to);
    }
}