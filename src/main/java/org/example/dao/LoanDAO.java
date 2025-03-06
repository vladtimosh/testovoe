package dao;

import model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/Library";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "234500239";

    // Метод для получения подключения к базе данных
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Метод для вставки нового займа
    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO Loans (book_id, member_id, loan_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getMemberId()); // Изменено на memberId
            statement.setDate(3, new java.sql.Date(loan.getLoanDate().getTime())); // Преобразование
            statement.setDate(4, new java.sql.Date(loan.getReturnDate().getTime())); // Преобразование
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения займа по ID
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
                        resultSet.getInt("member_id"), // Изменено на memberId
                        resultSet.getDate("loan_date"),
                        resultSet.getDate("return_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loan;
    }

    // Метод для получения всех займов
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
                        resultSet.getInt("member_id"), // Изменено на memberId
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

    // Метод для обновления займа
    public void updateLoan(Loan loan) {
        String sql = "UPDATE Loans SET book_id = ?, member_id = ?, loan_date = ?, return_date = ? WHERE loan_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getMemberId()); // Изменено на memberId
            statement.setDate(3, new java.sql.Date(loan.getLoanDate().getTime())); // Преобразование
            statement.setDate(4, new java.sql.Date(loan.getReturnDate().getTime())); // Преобразование
            statement.setInt(5, loan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления займа по ID
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