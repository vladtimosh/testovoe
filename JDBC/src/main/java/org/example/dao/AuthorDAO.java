package org.example.dao;

import org.example.model.Author;
import org.example.model.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    public AuthorDAO() {
        this.jdbcURL = DatabaseProperties.getJdbcURL();
        this.jdbcUsername = DatabaseProperties.getJdbcUsername();
        this.jdbcPassword = DatabaseProperties.getJdbcPassword();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertAuthor(Author author) {
        String sql = "INSERT INTO Authors (name, birth_date) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            // Обработка Optional<LocalDate>
            if (author.getBirthDate().isPresent()) {
                statement.setDate(2, java.sql.Date.valueOf(author.getBirthDate().get()));
            } else {
                statement.setNull(2, Types.DATE); // Установка NULL, если дата отсутствует
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting author: " + e.getMessage());
        }
    }

    public Author getAuthor(int id) {
        Author author = null;
        String sql = "SELECT * FROM Authors WHERE author_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth_date") != null ?
                                resultSet.getDate("birth_date").toLocalDate() : null // Обработка null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching author: " + e.getMessage());
        }
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM Authors";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth_date") != null ?
                                resultSet.getDate("birth_date").toLocalDate() : null // Обработка null
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching authors: " + e.getMessage());
        }
        return authors;
    }

    public void updateAuthor(Author author) {
        String sql = "UPDATE Authors SET name = ?, birth_date = ? WHERE author_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            // Обработка Optional<LocalDate>
            if (author.getBirthDate().isPresent()) {
                statement.setDate(2, java.sql.Date.valueOf(author.getBirthDate().get()));
            } else {
                statement.setNull(2, Types.DATE); // Установка NULL, если дата отсутствует
            }
            statement.setInt(3, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating author: " + e.getMessage());
        }
    }

    public void deleteAuthor(int id) {
        String sql = "DELETE FROM Authors WHERE author_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting author: " + e.getMessage());
        }
    }
}