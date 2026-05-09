package org.mns.dao;

import org.mns.db.DatabaseManager;
import org.mns.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * JDBC implementace rozhraní {@link CustomerDao}.
 */
public class CustomerDaoImpl implements CustomerDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> getByEmail(String email) throws Exception {
        String sql = "SELECT * FROM customer WHERE email = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            Optional<Customer> customer = getCustomer(stmt);

            if (customer.isPresent()) return customer;
        }

        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> getById(int id) throws Exception {
        String sql = "SELECT * FROM customer WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            Optional<Customer> customer = getCustomer(stmt);

            if (customer.isPresent()) return customer;
        }

        return Optional.empty();
    }

    /**
     * Pomocná metoda pro mapování ResultSetu na objekt Customer.
     *
     * @param stmt Připravený příkaz s výsledkem.
     * @return Optional se zákazníkem.
     * @throws SQLException Při chybě mapování polí.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String firstName, String lastName, String email, String phoneNumber, String password) throws Exception {
        String sql = "INSERT INTO customer (first_name, last_name, email, phone_number, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, password);

            stmt.executeUpdate();
        }
    }
}