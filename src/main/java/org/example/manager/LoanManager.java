package manager;

import dao.LoanDAO;
import model.Loan;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class LoanManager {
    private LoanDAO loanDAO;

    public LoanManager(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    public void manageLoans(Scanner scanner) {
        int loanChoice;

        do {
            System.out.println("\n--- Управление займами ---");
            System.out.println("1. Добавить займ");
            System.out.println("2. Просмотреть займ");
            System.out.println("3. Просмотреть все займы");
            System.out.println("4. Обновить займ");
            System.out.println("5. Удалить займ");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");
            loanChoice = scanner.nextInt();

            switch (loanChoice) {
                case 1:
                    addLoan(scanner);
                    break;
                case 2:
                    viewLoan(scanner);
                    break;
                case 3:
                    viewAllLoans();
                    break;
                case 4:
                    updateLoan(scanner);
                    break;
                case 5:
                    deleteLoan(scanner);
                    break;
                case 6:
                    System.out.println("Возвращение в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (loanChoice != 6);
    }

    private void addLoan(Scanner scanner) {
        System.out.print("Введите ID книги: ");
        int bookId = scanner.nextInt();
        System.out.print("Введите ID члена: "); // Изменено на memberId
        int memberId = scanner.nextInt();
        System.out.print("Введите дату займа (YYYY-MM-DD): ");
        String loanDateStr = scanner.next();
        System.out.print("Введите дату возврата (YYYY-MM-DD): ");
        String returnDateStr = scanner.next();

        Loan loan = new Loan(0, bookId, memberId, Date.valueOf(loanDateStr), Date.valueOf(returnDateStr)); // Изменено на memberId
        loanDAO.insertLoan(loan);
        System.out.println("Займ успешно добавлен!");
    }

    private void viewLoan(Scanner scanner) {
        System.out.print("Введите ID займа: ");
        int id = scanner.nextInt();
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
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        }
    }

    private void updateLoan(Scanner scanner) {
        System.out.print("Введите ID займа для обновления: ");
        int id = scanner.nextInt();
        Loan loan = loanDAO.getLoan(id);
        if (loan != null) {
            System.out.print("Введите новый ID книги: ");
            int newBookId = scanner.nextInt();
            System.out.print("Введите новый ID члена: "); // Изменено на memberId
            int newMemberId = scanner.nextInt();
            System.out.print("Введите новую дату займа (YYYY-MM-DD): ");
            String newLoanDateStr = scanner.next();
            System.out.print("Введите новую дату возврата (YYYY-MM-DD): ");
            String newReturnDateStr = scanner.next();

            loan.setBookId(newBookId);
            loan.setMemberId(newMemberId); // Изменено на setMemberId
            loan.setLoanDate(Date.valueOf(newLoanDateStr));
            loan.setReturnDate(Date.valueOf(newReturnDateStr));
            loanDAO.updateLoan(loan);
            System.out.println("Займ успешно обновлен!");
        } else {
            System.out.println("Займ не найден.");
        }
    }

    private void deleteLoan(Scanner scanner) {
        System.out.print("Введите ID займа для удаления: ");
        int id = scanner.nextInt();
        loanDAO.deleteLoan(id);
        System.out.println("Займ успешно удален!");
    }
}