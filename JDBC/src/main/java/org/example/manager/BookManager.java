package org.example.manager;

import org.example.dao.BookDAO;
import org.example.model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private final BookDAO bookDAO;

    public BookManager(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void manageBooks(Scanner scanner) {
        int bookChoice;

        do {
            displayMenu();
            bookChoice = getValidInteger(scanner);

            switch (bookChoice) {
                case 1 -> addBook(scanner);
                case 2 -> viewBook(scanner);
                case 3 -> viewAllBooks();
                case 4 -> updateBook(scanner);
                case 5 -> deleteBook(scanner);
                case 6 -> System.out.println("Возвращение в главное меню...");
                default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (bookChoice != 6);
    }

    private void displayMenu() {
        System.out.println("\n--- Управление книгами ---");
        System.out.println("1. Добавить книгу");
        System.out.println("2. Просмотреть книгу");
        System.out.println("3. Просмотреть все книги");
        System.out.println("4. Обновить книгу");
        System.out.println("5. Удалить книгу");
        System.out.println("6. Вернуться в главное меню");
        System.out.print("Выберите опцию: ");
    }

    private void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите дату публикации (YYYY-MM-DD): ");
        String publicationDateStr = scanner.nextLine();
        System.out.print("Введите ID жанра: ");
        int genreId = getValidInteger(scanner);

        try {
            LocalDate publicationDate = LocalDate.parse(publicationDateStr);
            Book book = new Book(0, title, publicationDate, genreId);
            bookDAO.insertBook(book);
            System.out.println("Книга успешно добавлена!");
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
        }
    }

    private void viewBook(Scanner scanner) {
        System.out.print("Введите ID книги: ");
        int id = getValidInteger(scanner);
        Book book = bookDAO.getBook(id);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Книга не найдена.");
        }
    }

    private void viewAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Нет доступных книг.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void updateBook(Scanner scanner) {
        System.out.print("Введите ID книги для обновления: ");
        int id = getValidInteger(scanner);
        Book book = bookDAO.getBook(id);
        if (book != null) {
            System.out.print("Введите новое название книги: ");
            String newTitle = scanner.nextLine();
            System.out.print("Введите новую дату публикации (YYYY-MM-DD): ");
            String newPublicationDateStr = scanner.nextLine();
            System.out.print("Введите новый ID жанра: ");
            int newGenreId = getValidInteger(scanner);

            try {
                LocalDate newPublicationDate = LocalDate.parse(newPublicationDateStr);
                book.setTitle(newTitle);
                book.setPublicationDate(newPublicationDate);
                book.setGenreId(newGenreId);
                bookDAO.updateBook(book);
                System.out.println("Книга успешно обновлена!");
            } catch (Exception e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
            }
        } else {
            System.out.println("Книга не найдена.");
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Введите ID книги для удаления: ");
        int id = getValidInteger(scanner);
        bookDAO.deleteBook(id);
        System.out.println("Книга успешно удалена!");
    }

    private int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Пожалуйста, введите целое число: ");
            }
        }
    }
}