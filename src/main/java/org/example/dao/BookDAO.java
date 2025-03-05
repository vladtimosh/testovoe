package dao;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/your_database";
    private String jdbcUsername = "your_username";
    private String jdbcPassword = "your_password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO Books (title, published_date, genre_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getPublishedDate());
            statement.setInt(3, book.getGenreId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBook(int id) {
        Book book = null;
        String sql = "SELECT * FROM Books WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("published_date"),
                        resultSet.getInt("genre_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("published_date"),
                        resultSet.getInt("genre_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, published_date = ?, genre_id = ? WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getPublishedDate());
            statement.setInt(3, book.getGenreId());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}