package org.mns.service;

import org.mns.dao.CustomerDao;
import org.mns.dto.LoginCredentials;
import org.mns.model.Customer;

import java.util.Optional;

/**
 * Implementace služby pro správu zákazníků.
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Customer> login(LoginCredentials credentials) throws Exception {
        Optional<Customer> customer = customerDao.getByEmail(credentials.email());

        if (customer.isEmpty()) {
            return Optional.empty();
        }

        if (!customer.get().getPassword().equals(credentials.password())) {
            return Optional.empty();
        }

        return customer;
    }

    /**
     * {@inheritDoc}
     */
    public void register(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception {
        Optional<Customer> existing = customerDao.getByEmail(email);

        if (existing.isPresent()) {
            throw new IllegalArgumentException("Účet s tímto e-mailem již existuje.");
        }

        customerDao.create(firstName, lastName, email, phoneNumber, password);
    }
}