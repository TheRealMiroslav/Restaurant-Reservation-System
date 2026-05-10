package org.mns.service;

import org.mns.dto.LoginCredentials;
import org.mns.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> login(LoginCredentials credentials) throws Exception;

    void register(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception;
}
