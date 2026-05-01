package org.mns.dao;

import org.mns.model.Customer;

import java.util.Optional;

public interface CustomerDao {
    Optional<Customer> getByEmail(String email) throws Exception;

    Optional<Customer> getById(int id) throws Exception;

    void create(Customer customer) throws Exception;
}