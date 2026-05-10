package org.mns.model;

/**
 * Entita reprezentující recenzi restaurace od zákazníka.
 */
public class Review {
    protected int id;
    protected int customerId;
    protected int restaurantId;
    protected double rating;
    protected String comment;

    public Review(int customerId, int restaurantId, double rating, String comment) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
