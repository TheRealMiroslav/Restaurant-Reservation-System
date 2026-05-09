package org.mns.model;

/**
 * Entita reprezentující zákazníka restaurace.
 */
public class Customer extends Person {
    public Customer(int id, String firstName, String lastName, String email, String phoneNumber, String password) {
        super(id, firstName, lastName, email, phoneNumber, password);
    }

    public Customer(int id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName, email, phoneNumber);
    }

    public Customer() {
        super();
    }
}
