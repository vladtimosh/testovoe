package dao;

import model.Author;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/Library";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "234500239";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertAuthor(Author author) {
        String sql = "INSERT INTO Authors (name, birth_date) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            statement.setDate(2, author.getBirthDate()); // Используем java.sql.Date

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                        resultSet.getDate("birth_date") // Получаем java.sql.Date
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                        resultSet.getDate("birth_date") // Получаем java.sql.Date
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void updateAuthor(Author author) {
        String sql = "UPDATE Authors SET name = ?, birth_date = ? WHERE author_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            statement.setDate(2, author.getBirthDate()); // Установка java.sql.Date
            statement.setInt(3, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int id) {
        String sql = "DELETE FROM Authors WHERE author_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}