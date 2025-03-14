package org.example.manager; // Убедитесь, что это правильно

import org.example.dao.LoanDAO; // Импортируйте LoanDAO
import org.example.model.Loan; // Импортируйте Loan

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LoanManager {
    private final LoanDAO loanDAO;

    public LoanManager(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    public void manageLoans(Scanner scanner) {
        int loanChoice;

        do {
            displayMenu();
            loanChoice = getValidInteger(scanner);

            switch (loanChoice) {
                case 1 -> addLoan(scanner);
                case 2 -> viewLoan(scanner);
                case 3 -> viewAllLoans();
                case 4 -> updateLoan(scanner);
                case 5 -> deleteLoan(scanner);
                case 6 -> System.out.println("Возвращение в главное меню...");
                default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (loanChoice != 6);
    }

    private void displayMenu() {
        System.out.println("\n--- Управление займами ---");
        System.out.println("1. Добавить займ");
        System.out.println("2. Просмотреть займ");
        System.out.println("3. Просмотреть все займы");
        System.out.println("4. Обновить займ");
        System.out.println("5. Удалить займ");
        System.out.println("6. Вернуться в главное меню");
        System.out.print("Выберите опцию: ");
    }

    private void addLoan(Scanner scanner) {
        System.out.print("Введите ID книги: ");
        int bookId = getValidInteger(scanner);
        System.out.print("Введите ID члена: ");
        int memberId = getValidInteger(scanner);
        System.out.print("Введите дату займа (YYYY-MM-DD): ");
        String loanDateStr = scanner.next();
        System.out.print("Введите дату возврата (YYYY-MM-DD): ");
        String returnDateStr = scanner.next();

        try {
            LocalDate loanDate = LocalDate.parse(loanDateStr);
            LocalDate returnDate = LocalDate.parse(returnDateStr);
            Loan loan = new Loan(0, bookId, memberId, loanDate, returnDate);
            loanDAO.insertLoan(loan);
            System.out.println("Займ успешно добавлен!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
        }
    }

    private void viewLoan(Scanner scanner) {
        System.out.print("Введите ID займа: ");
        int id = getValidInteger(scanner);
        Loan loan = loanDAO.getLoan(id);
        if (loan != null) {
            System.out.println(loan);
        } else {
            System.out.println("Займ не найден.");
        }
    }

    private void viewAllLoans() {
        List<Loan> loans = loanDAO.getAllLoans();
        if (loans.isEmpty()) {
            System.out.println("Нет доступных займов.");
        } else {
            loans.forEach(System.out::println);
        }
    }

    private void updateLoan(Scanner scanner) {
        System.out.print("Введите ID займа для обновления: ");
        int id = getValidInteger(scanner);
        Loan loan = loanDAO.getLoan(id);
        if (loan != null) {
            System.out.print("Введите новый ID книги: ");
            int newBookId = getValidInteger(scanner);
            System.out.print("Введите новый ID члена: ");
            int newMemberId = getValidInteger(scanner);
            System.out.print("Введите новую дату займа (YYYY-MM-DD): ");
            String newLoanDateStr = scanner.next();
            System.out.print("Введите новую дату возврата (YYYY-MM-DD): ");
            String newReturnDateStr = scanner.next();

            try {
                loan.setBookId(newBookId);
                loan.setMemberId(newMemberId);
                loan.setLoanDate(LocalDate.parse(newLoanDateStr));
                loan.setReturnDate(LocalDate.parse(newReturnDateStr));
                loanDAO.updateLoan(loan);
                System.out.println("Займ успешно обновлен!");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD.");
            }
        } else {
            System.out.println("Займ не найден.");
        }
    }

    private void deleteLoan(Scanner scanner) {
        System.out.print("Введите ID займа для удаления: ");
        int id = getValidInteger(scanner);
        loanDAO.deleteLoan(id);
        System.out.println("Займ успешно удален!");
    }

    private int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next().trim());
            } catch (NumberFormatException e) {
                System.out.print("Пожалуйста, введите целое число: ");
            }
        }
    }
}