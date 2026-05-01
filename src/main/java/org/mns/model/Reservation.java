package org.mns.model;

import org.mns.model.state.ReservationState;
import org.mns.model.state.UnconfirmedReservationState;

import java.sql.Timestamp;

public class Reservation {
    protected int id;
    protected int customerId;
    protected int tableId;

    protected Timestamp startTime;
    protected Timestamp endTime;

    protected String comment;
    protected int numOfPeople;

    protected ReservationState status;

    public Reservation(int id, int customerId, int tableId, Timestamp startTime, Timestamp endTime, String comment, int numOfPeople) {
        this.id = id;
        this.customerId = customerId;
        this.tableId = tableId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.numOfPeople = numOfPeople;
        this.status = new UnconfirmedReservationState();
    }

    public Reservation(int customerId, int tableId, Timestamp startTime, Timestamp endTime, String comment, int numOfPeople) {
        this.customerId = customerId;
        this.tableId = tableId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.numOfPeople = numOfPeople;
        this.status = new UnconfirmedReservationState();
    }

    public Reservation() {

    }

    public void confirm() {
        status.confirm(this);
    }

    public void cancel() {
        status.cancel(this);
    }

    public void complete() {
        status.complete(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public ReservationState getStatus() {
        return status;
    }

    public void setStatus(ReservationState status) {
        this.status = status;
    }
}
