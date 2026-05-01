package org.mns.builder;

import org.mns.model.Reservation;
import java.sql.Timestamp;

public class ReservationBuilder {
    private int customerId;
    private int tableId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String comment;
    private int numOfPeople;

    public ReservationBuilder setCustomer(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public ReservationBuilder setTable(int tableId) {
        this.tableId = tableId;
        return this;
    }

    public ReservationBuilder setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public ReservationBuilder setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public ReservationBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ReservationBuilder setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
        return this;
    }

    public Reservation build() {
        // Časová validace
        if (startTime != null && endTime != null && startTime.after(endTime)) {
            throw new IllegalArgumentException("Začátek rezervace je po jejím konci.");
        }

        if (startTime != null && startTime.before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Nelze rezervovat v minulosti.");
        }

        return new Reservation(customerId, tableId, startTime, endTime, comment, numOfPeople);
    }
}