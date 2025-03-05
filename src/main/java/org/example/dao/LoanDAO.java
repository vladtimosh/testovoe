package dao;

import model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/your_database";
    private String jdbcUsername = "your_username";
    private String jdbcPassword = "your_password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO Loans (book_id, user_id, loan_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getUserId());
            statement.setDate(3, new Date(loan.getLoanDate().getTime()));
            statement.setDate(4, new Date(loan.getReturnDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                        resultSet.getInt("user_id"),
                        resultSet.getDate("loan_date"),
                        resultSet.getDate("return_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                        resultSet.getInt("user_id"),
                        resultSet.getDate("loan_date"),
                        resultSet.getDate("return_date")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    public void updateLoan(Loan loan) {
        String sql = "UPDATE Loans SET book_id = ?, user_id = ?, loan_date = ?, return_date = ? WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getUserId());
            statement.setDate(3, new Date(loan.getLoanDate().getTime()));
            statement.setDate(4, new Date(loan.getReturnDate().getTime()));
            statement.setInt(5, loan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLoan(int id) {
        String sql = "DELETE FROM Loans WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}