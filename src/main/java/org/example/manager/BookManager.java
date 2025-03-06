package manager;

import dao.BookDAO;
import model.Book;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private BookDAO bookDAO;

    public BookManager(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void manageBooks(Scanner scanner) {
        int bookChoice;

        do {
            System.out.println("\n--- Управление книгами ---");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Просмотреть книгу");
            System.out.println("3. Просмотреть все книги");
            System.out.println("4. Обновить книгу");
            System.out.println("5. Удалить книгу");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");
            bookChoice = scanner.nextInt();

            switch (bookChoice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewBook(scanner);
                    break;
                case 3:
                    viewAllBooks();
                    break;
                case 4:
                    updateBook(scanner);
                    break;
                case 5:
                    deleteBook(scanner);
                    break;
                case 6:
                    System.out.println("Возвращение в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (bookChoice != 6);
    }

    private void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.next();
        System.out.print("Введите дату публикации (YYYY-MM-DD): ");
        String publishedDateStr = scanner.next();
        System.out.print("Введите ID жанра: ");
        int genreId = scanner.nextInt();

        try {
            // Преобразование строки даты в java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(publishedDateStr);
            Date publishedDate = new Date(utilDate.getTime());

            Book book = new Book(0, title, publishedDate, genreId); // Создаем объект книги
            bookDAO.insertBook(book); // Вставляем книгу в базу данных
            System.out.println("Книга успешно добавлена!");
        } catch (ParseException e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
        }
    }

    private void viewBook(Scanner scanner) {
        System.out.print("Введите ID книги: ");
        int id = scanner.nextInt();
        Book book = bookDAO.getBook(id);
        if (book != null) {
            System.out.println(book); // Выводим информацию о книге
        } else {
            System.out.println("Книга не найдена.");
        }
    }

    private void viewAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Нет доступных книг.");
        } else {
            for (Book book : books) {
                System.out.println(book); // Выводим информацию о каждой книге
            }
        }
    }

    private void updateBook(Scanner scanner) {
        System.out.print("Введите ID книги для обновления: ");
        int id = scanner.nextInt();
        Book book = bookDAO.getBook(id);
        if (book != null) {
            System.out.print("Введите новое название книги: ");
            String newTitle = scanner.next();
            System.out.print("Введите новую дату публикации (YYYY-MM-DD): ");
            String newPublishedDateStr = scanner.next();
            System.out.print("Введите новый ID жанра: ");
            int newGenreId = scanner.nextInt();

            try {
                // Преобразование строки даты в java.sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(newPublishedDateStr);
                Date newPublishedDate = new Date(utilDate.getTime());

                // Обновляем данные книги
                book.setTitle(newTitle);
                book.setPublishedDate(newPublishedDate);
                book.setGenreId(newGenreId);
                bookDAO.updateBook(book); // Обновляем книгу в базе данных
                System.out.println("Книга успешно обновлена!");
            } catch (ParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
            }
        } else {
            System.out.println("Книга не найдена.");
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Введите ID книги для удаления: ");
        int id = scanner.nextInt();
        bookDAO.deleteBook(id); // Удаляем книгу из базы данных
        System.out.println("Книга успешно удалена!");
    }
}