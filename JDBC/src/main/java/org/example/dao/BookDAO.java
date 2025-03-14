package org.example.dao;

import org.example.model.Book;
import org.example.model.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class BookDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    // Конструктор с параметрами
    public BookDAO() {
        this.jdbcURL = DatabaseProperties.getJdbcURL();
        this.jdbcUsername = DatabaseProperties.getJdbcUsername();
        this.jdbcPassword = DatabaseProperties.getJdbcPassword();
    }

    // Метод для загрузки конфигурации из файла, если это необходимо
    // public BookDAO() {
    //     loadDatabaseConfig();
    // }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO books (title, published_date, genre_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setDate(2, java.sql.Date.valueOf(book.getPublicationDate()));
            statement.setInt(3, book.getGenreId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting book: " + e.getMessage());
        }
    }

    public Book getBook(int id) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getDate("published_date").toLocalDate(),
                        resultSet.getInt("genre_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching book: " + e.getMessage());
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getDate("published_date").toLocalDate(),
                        resultSet.getInt("genre_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, published_date = ?, genre_id = ? WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setDate(2, java.sql.Date.valueOf(book.getPublicationDate()));
            statement.setInt(3, book.getGenreId());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}