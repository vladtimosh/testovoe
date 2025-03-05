import dao.AuthorDAO;
import dao.BookDAO;
import dao.GenreDAO;
import dao.LoanDAO;
import dao.MemberDAO;
import manager.AuthorManager;
import manager.BookManager;
import manager.GenreManager;
import manager.LoanManager;
import manager.MemberManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        AuthorDAO authorDAO = new AuthorDAO();
        GenreDAO genreDAO = new GenreDAO();
        LoanDAO loanDAO = new LoanDAO();
        MemberDAO memberDAO = new MemberDAO();

        BookManager bookManager = new BookManager(bookDAO);
        AuthorManager authorManager = new AuthorManager(authorDAO);
        GenreManager genreManager = new GenreManager(genreDAO);
        LoanManager loanManager = new LoanManager(loanDAO);
        MemberManager memberManager = new MemberManager(memberDAO);

        int choice;

        do {
            System.out.println("1. Управление книгами");
            System.out.println("2. Управление авторами");
            System.out.println("3. Управление жанрами");
            System.out.println("4. Управление займами");
            System.out.println("5. Управление членами");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookManager.manageBooks(scanner);
                    break;
                case 2:
                    authorManager.manageAuthors(scanner);
                    break;
                case 3:
                    genreManager.manageGenres(scanner);
                    break;
                case 4:
                    loanManager.manageLoans(scanner);
                    break;
                case 5:
                    memberManager.manageMembers(scanner);
                    break;
                case 6:
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (choice != 6);
        scanner.close();
    }
}