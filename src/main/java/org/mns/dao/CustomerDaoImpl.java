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
        String sql = "SELECT * FROM Zakaznik WHERE email LIKE ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            Optional<Customer> customer = getCustomer(stmt);

            if (customer.isPresent()) return customer;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Customer> getById(int id) throws Exception {
        String sql = "SELECT * FROM Zakaznik WHERE id LIKE ?";

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
            customer.setFirstName(rs.getString("jmeno"));
            customer.setLastName(rs.getString("prijmeni"));
            customer.setEmail(rs.getString("email"));
            customer.setPhoneNumber(rs.getString("telefonniCislo"));
            customer.setPassword(rs.getString("heslo"));

            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public void create(Customer customer) throws Exception {
        String sql = "INSERT INTO Zakaznik (id, jmeno, prijmeni, email, telefonniCislo, heslo) VALUES (?, ?, ?, ?, ?, ?)";

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