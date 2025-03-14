package org.example.dao;

import org.example.model.DatabaseProperties;
import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    // Конструктор с параметрами
    public GenreDAO() {
        this.jdbcURL = DatabaseProperties.getJdbcURL();
        this.jdbcUsername = DatabaseProperties.getJdbcUsername();
        this.jdbcPassword = DatabaseProperties.getJdbcPassword();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertGenre(Genre genre) {
        String sql = "INSERT INTO genres (genre_name) VALUES (?)"; // Изменено на genre_name
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getGenreName()); // Изменено на getGenreName
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting genre: " + e.getMessage());
        }
    }

    public Genre getGenre(int id) {
        Genre genre = null;
        String sql = "SELECT * FROM genres WHERE genre_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new Genre(
                        resultSet.getInt("genre_id"),
                        resultSet.getString("genre_name") // Изменено на genre_name
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching genre: " + e.getMessage());
        }
        return genre;
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt("genre_id"),
                        resultSet.getString("genre_name") // Изменено на genre_name
                );
                genres.add(genre);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching genres: " + e.getMessage());
        }
        return genres;
    }

    public void updateGenre(Genre genre) {
        String sql = "UPDATE genres SET genre_name = ? WHERE genre_id = ?"; // Изменено на genre_name
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getGenreName()); // Изменено на getGenreName
            statement.setInt(2, genre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating genre: " + e.getMessage());
        }
    }

    public void deleteGenre(int id) {
        String sql = "DELETE FROM genres WHERE genre_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting genre: " + e.getMessage());
        }
    }
}