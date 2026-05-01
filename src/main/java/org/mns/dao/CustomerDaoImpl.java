package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Optional<Customer> getByEmail(String email) throws Exception {
        String sql = "SELECT * FROM customer WHERE email LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            Optional<Customer> customer = getCustomer(stmt);

            if (customer.isPresent()) return customer;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Customer> getById(int id) throws Exception {
        String sql = "SELECT * FROM customer WHERE id LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            Optional<Customer> customer = getCustomer(stmt);

            if (customer.isPresent()) return customer;
        }

        return Optional.empty();
    }

    private Optional<Customer> getCustomer(PreparedStatement stmt) throws SQLException {
        var rs = stmt.executeQuery();

        if (rs.next()) {
            Customer customer = new Customer();

            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhoneNumber(rs.getString("phone_number"));
            customer.setPassword(rs.getString("password"));

            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public void create(Customer customer) throws Exception {
        String sql = "INSERT INTO customer (id, first_name, last_name, email, phone_number, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getPassword());

            stmt.executeUpdate();
        }
    }
}