package dao;

import model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/your_database";
    private String jdbcUsername = "your_username";
    private String jdbcPassword = "your_password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertGenre(Genre genre) {
        String sql = "INSERT INTO Genres (name) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Genre getGenre(int id) {
        Genre genre = null;
        String sql = "SELECT * FROM Genres WHERE genre_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new Genre(
                        resultSet.getInt("genre_id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM Genres";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt("genre_id"),
                        resultSet.getString("name")
                );
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public void updateGenre(Genre genre) {
        String sql = "UPDATE Genres SET name = ? WHERE genre_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.setInt(2, genre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGenre(int id) {
        String sql = "DELETE FROM Genres WHERE genre_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}