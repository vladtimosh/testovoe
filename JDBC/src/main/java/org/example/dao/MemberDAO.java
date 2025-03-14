package org.example.dao;

import org.example.model.DatabaseProperties;
import org.example.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    // Конструктор с параметрами
    public MemberDAO() {
        this.jdbcURL = DatabaseProperties.getJdbcURL();
        this.jdbcUsername = DatabaseProperties.getJdbcUsername();
        this.jdbcPassword = DatabaseProperties.getJdbcPassword();
    }

    // Метод для получения соединения с базой данных
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Метод для вставки нового члена
    public void insertMember(Member member) {
        String sql = "INSERT INTO members (full_name, membership_date) VALUES (?, ?)"; // Убрано email
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getFullName()); // Используем getFullName
            statement.setDate(2, Date.valueOf(member.getMembershipDate())); // Преобразование LocalDate в java.sql.Date
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting member: " + e.getMessage());
        }
    }

    // Метод для получения члена по ID
    public Member getMember(int id) {
        Member member = null;
        String sql = "SELECT * FROM members WHERE member_id = ?"; // Убрано email
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("full_name"), // Используем full_name
                        resultSet.getDate("membership_date").toLocalDate() // Преобразование java.sql.Date в LocalDate
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching member: " + e.getMessage());
        }
        return member;
    }

    // Метод для получения всех членов
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members"; // Убрано email
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("full_name"), // Используем full_name
                        resultSet.getDate("membership_date").toLocalDate() // Преобразование java.sql.Date в LocalDate
                );
                members.add(member);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching members: " + e.getMessage());
        }
        return members;
    }

    // Метод для обновления информации о члене
    public void updateMember(Member member) {
        String sql = "UPDATE members SET full_name = ?, membership_date = ? WHERE member_id = ?"; // Убрано email
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getFullName()); // Используем getFullName
            statement.setDate(2, Date.valueOf(member.getMembershipDate())); // Преобразование LocalDate в java.sql.Date
            statement.setInt(3, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
        }
    }

    // Метод для удаления члена по ID
    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE member_id = ?"; // Убрано email
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
        }
    }
}