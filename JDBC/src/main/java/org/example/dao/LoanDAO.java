package org.example.dao;

import org.example.model.DatabaseProperties;
import org.example.model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    // Конструктор с параметрами
    public LoanDAO() {
        this.jdbcURL = DatabaseProperties.getJdbcURL();
        this.jdbcUsername = DatabaseProperties.getJdbcUsername();
        this.jdbcPassword = DatabaseProperties.getJdbcPassword();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO Loans (book_id, member_id, loan_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getMemberId());
            statement.setDate(3, java.sql.Date.valueOf(loan.getLoanDate())); // Преобразование LocalDate в java.sql.Date
            statement.setDate(4, loan.getReturnDate() != null ? java.sql.Date.valueOf(loan.getReturnDate()) : null); // Проверка на null
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting loan: " + e.getMessage());
        }
    }

    public Loan getLoan(int id) {
        Loan loan = null;
        String sql = "SELECT * FROM Loans WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loan = new Loan(
                        resultSet.getInt("loan_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("member_id"),
                        resultSet.getDate("loan_date").toLocalDate(), // Преобразование java.sql.Date в LocalDate
                        resultSet.getDate("return_date") != null ? resultSet.getDate("return_date").toLocalDate() : null // Проверка на null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching loan: " + e.getMessage());
        }
        return loan;
    }

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM Loans";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Loan loan = new Loan(
                        resultSet.getInt("loan_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("member_id"),
                        resultSet.getDate("loan_date").toLocalDate(), // Преобразование java.sql.Date в LocalDate
                        resultSet.getDate("return_date") != null ? resultSet.getDate("return_date").toLocalDate() : null // Проверка на null
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching loans: " + e.getMessage());
        }
        return loans;
    }

    public void updateLoan(Loan loan) {
        String sql = "UPDATE Loans SET book_id = ?, member_id = ?, loan_date = ?, return_date = ? WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getMemberId());
            statement.setDate(3, java.sql.Date.valueOf(loan.getLoanDate())); // Преобразование LocalDate в java.sql.Date
            statement.setDate(4, loan.getReturnDate() != null ? java.sql.Date.valueOf(loan.getReturnDate()) : null); // Проверка на null
            statement.setInt(5, loan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating loan: " + e.getMessage());
        }
    }

    public void deleteLoan(int id) {
        String sql = "DELETE FROM Loans WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
        }
    }
}