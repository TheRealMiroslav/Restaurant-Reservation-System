package org.mns.ui;

import org.mns.model.Customer;

public class SessionContext {
    private Customer loggedInUser;

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public Customer getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Customer customer) {
        this.loggedInUser = customer;
    }

    public void logOut() {
        this.loggedInUser = null;
    }
}
