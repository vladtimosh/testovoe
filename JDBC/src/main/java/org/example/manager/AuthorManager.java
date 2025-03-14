package org.example.manager;

import org.example.dao.AuthorDAO;
import org.example.model.Author;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AuthorManager {
    private AuthorDAO authorDAO;

    public AuthorManager(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public void manageAuthors(Scanner scanner) {
        int authorChoice;

        do {
            System.out.println("\n--- Управление авторами ---");
            System.out.println("1. Добавить автора");
            System.out.println("2. Просмотреть автора");
            System.out.println("3. Просмотреть всех авторов");
            System.out.println("4. Обновить автора");
            System.out.println("5. Удалить автора");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка: пожалуйста, введите число.");
                scanner.next(); // Очистка неверного ввода
            }
            authorChoice = scanner.nextInt();

            switch (authorChoice) {
                case 1:
                    addAuthor(scanner);
                    break;
                case 2:
                    viewAuthor(scanner);
                    break;
                case 3:
                    viewAllAuthors();
                    break;
                case 4:
                    updateAuthor(scanner);
                    break;
                case 5:
                    deleteAuthor(scanner);
                    break;
                case 6:
                    System.out.println("Возвращение в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (authorChoice != 6);
    }

    private void addAuthor(Scanner scanner) {
        System.out.print("Введите имя автора: ");
        String name = scanner.next();
        System.out.print("Введите дату рождения (YYYY-MM-DD): ");
        String birthDateStr = scanner.next();

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            Author author = new Author(0, name, birthDate);
            authorDAO.insertAuthor(author);
            System.out.println("Автор успешно добавлен!");
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
        }
    }

    private void viewAuthor(Scanner scanner) {
        System.out.print("Введите ID автора: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка: пожалуйста, введите число.");
            scanner.next();
        }
        int id = scanner.nextInt();
        Author author = authorDAO.getAuthor(id);
        if (author != null) {
            System.out.println(author);
        } else {
            System.out.println("Автор не найден.");
        }
    }

    private void viewAllAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("Нет доступных авторов.");
        } else {
            for (Author author : authors) {
                System.out.println(author);
            }
        }
    }

    private void updateAuthor(Scanner scanner) {
        System.out.print("Введите ID автора для обновления: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка: пожалуйста, введите число.");
            scanner.next();
        }
        int id = scanner.nextInt();
        Author author = authorDAO.getAuthor(id);
        if (author != null) {
            System.out.print("Введите новое имя автора: ");
            String newName = scanner.next();
            System.out.print("Введите новую дату рождения (YYYY-MM-DD): ");
            String newBirthDateStr = scanner.next();

            try {
                LocalDate newBirthDate = LocalDate.parse(newBirthDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                author.setName(newName);
                author.setBirthDate(newBirthDate);
                authorDAO.updateAuthor(author);
                System.out.println("Автор успешно обновлен!");
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
            }
        } else {
            System.out.println("Автор не найден.");
        }
    }

    private void deleteAuthor(Scanner scanner) {
        System.out.print("Введите ID автора для удаления: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка: пожалуйста, введите число.");
            scanner.next();
        }
        int id = scanner.nextInt();
        authorDAO.deleteAuthor(id);
        System.out.println("Автор успешно удален!");
    }
}