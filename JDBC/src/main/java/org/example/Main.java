package org.example;

import org.example.dao.*;
import org.example.manager.*;
import org.example.model.DatabaseProperties;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static BookDAO bookDAO;
    private static AuthorDAO authorDAO;
    private static GenreDAO genreDAO;
    private static LoanDAO loanDAO;
    private static MemberDAO memberDAO;

    private static BookManager bookManager;
    private static AuthorManager authorManager;
    private static GenreManager genreManager;
    private static LoanManager loanManager;
    private static MemberManager memberManager;

    public static void main(String[] args) {
        // Загрузка свойств базы данных

        DatabaseProperties.loadProperties();

        // Инициализация DAO с параметрами подключения
        initializeDAOs();

        // Инициализация менеджеров
        initializeManagers();

        // Основной цикл
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                displayMenu();
                choice = getUserChoice(scanner);
                handleUserChoice(choice, scanner);
            } while (choice != 6);
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void initializeDAOs() {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        genreDAO = new GenreDAO();
        loanDAO = new LoanDAO();
        memberDAO = new MemberDAO();
    }

    private static void initializeManagers() {
        bookManager = new BookManager(bookDAO);
        authorManager = new AuthorManager(authorDAO);
        genreManager = new GenreManager(genreDAO);
        loanManager = new LoanManager(loanDAO);
        memberManager = new MemberManager(memberDAO);
    }

    private static void displayMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1. Управление книгами");
        System.out.println("2. Управление авторами");
        System.out.println("3. Управление жанрами");
        System.out.println("4. Управление займами");
        System.out.println("5. Управление членами");
        System.out.println("6. Выход");
        System.out.print("Выберите опцию: ");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Неверный выбор. Пожалуйста, выберите число от 1 до 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.next(); // Очистка неверного ввода
            }
        }
        return choice;
    }

    private static void handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> bookManager.manageBooks(scanner);
            case 2 -> authorManager.manageAuthors(scanner);
            case 3 -> genreManager.manageGenres(scanner);
            case 4 -> loanManager.manageLoans(scanner);
            case 5 -> memberManager.manageMembers(scanner);
            case 6 -> System.out.println("Выход...");
            default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
        }
    }
}