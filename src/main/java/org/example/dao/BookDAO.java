package dao;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/Library";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "234500239";

    // Метод для получения подключения к базе данных
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Метод для вставки книги в базу данных
    public void insertBook(Book book) {
        String sql = "INSERT INTO books (title, published_date, genre_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setDate(2, new java.sql.Date(book.getPublishedDate().getTime())); // Преобразование в java.sql.Date
            statement.setInt(3, book.getGenreId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения книги по ID
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
                        resultSet.getDate("published_date"), // Получаем java.sql.Date
                        resultSet.getInt("genre_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // Метод для получения всех книг
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
                        resultSet.getDate("published_date"), // Получаем java.sql.Date
                        resultSet.getInt("genre_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Метод для обновления книги
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, published_date = ?, genre_id = ? WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setDate(2, new java.sql.Date(book.getPublishedDate().getTime())); // Устанавливаем java.sql.Date
            statement.setInt(3, book.getGenreId());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления книги по ID
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}