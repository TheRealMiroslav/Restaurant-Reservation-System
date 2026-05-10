package org.mns.service;

import org.mns.dao.CustomerDao;
import org.mns.dto.LoginCredentials;
import org.mns.model.Customer;

import java.util.Optional;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Optional<Customer> login(LoginCredentials credentials) throws Exception {
        Optional<Customer> customer = customerDao.getByEmail(credentials.getEmail());

        if (customer.isEmpty()) {
            return Optional.empty();
        }

        if (!customer.get().getPassword().equals(credentials.getPassword())) {
            return Optional.empty();
        }

        return customer;
    }

    public void register(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception {
        Optional<Customer> existing = customerDao.getByEmail(email);

        if (existing.isPresent()) {
            throw new IllegalArgumentException("Účet s tímto e-mailem již existuje.");
        }

        customerDao.create(firstName, lastName, email, phoneNumber, password);
    }
}