package org.example.manager;

import org.example.dao.MemberDAO;
import org.example.model.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    private final MemberDAO memberDAO;

    public MemberManager(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void manageMembers(Scanner scanner) {
        int memberChoice;

        do {
            displayMenu();
            memberChoice = getValidInteger(scanner);

            switch (memberChoice) {
                case 1 -> addMember(scanner);
                case 2 -> viewMember(scanner);
                case 3 -> viewAllMembers();
                case 4 -> updateMember(scanner);
                case 5 -> deleteMember(scanner);
                case 6 -> System.out.println("Возвращение в главное меню...");
                default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (memberChoice != 6);
    }

    private void displayMenu() {
        System.out.println("\n--- Управление членами ---");
        System.out.println("1. Добавить члена");
        System.out.println("2. Просмотреть члена");
        System.out.println("3. Просмотреть всех членов");
        System.out.println("4. Обновить члена");
        System.out.println("5. Удалить члена");
        System.out.println("6. Вернуться в главное меню");
        System.out.print("Выберите опцию: ");
    }

    private void addMember(Scanner scanner) {
        System.out.print("Введите полное имя члена: ");
        String fullName = scanner.nextLine().trim();
        if (fullName.isEmpty()) {
            System.out.println("Ошибка: имя не может быть пустым.");
            return;
        }

        System.out.print("Введите дату членства (YYYY-MM-DD): ");
        LocalDate membershipDate = getValidDate(scanner);

        // Используем ID 0 для автоинкремента в базе данных
        Member member = new Member(0, fullName, membershipDate);
        memberDAO.insertMember(member);
        System.out.println("Член успешно добавлен!");
    }

    private void viewMember(Scanner scanner) {
        System.out.print("Введите ID члена: ");
        int id = getValidInteger(scanner);
        Member member = memberDAO.getMember(id);
        if (member != null) {
            System.out.println("Член: " + member.getFullName() + ", Дата членства: " + member.getMembershipDate());
        } else {
            System.out.println("Член не найден.");
        }
    }

    private void viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("Нет доступных членов.");
        } else {
            members.forEach(member ->
                    System.out.println("ID: " + member.getId() + ", Имя: " + member.getFullName() + ", Дата членства: " + member.getMembershipDate()));
        }
    }

    private void updateMember(Scanner scanner) {
        System.out.print("Введите ID члена для обновления: ");
        int id = getValidInteger(scanner);
        Member member = memberDAO.getMember(id);
        if (member != null) {
            System.out.print("Введите новое полное имя члена: ");
            String newFullName = scanner.nextLine().trim();
            if (newFullName.isEmpty()) {
                System.out.println("Ошибка: имя не может быть пустым.");
                return;
            }

            System.out.print("Введите новую дату членства (YYYY-MM-DD): ");
            LocalDate newMembershipDate = getValidDate(scanner);

            member.setFullName(newFullName); // Обновлено на setFullName
            member.setMembershipDate(newMembershipDate);
            memberDAO.updateMember(member);
            System.out.println("Член успешно обновлен!");
        } else {
            System.out.println("Член не найден.");
        }
    }

    private void deleteMember(Scanner scanner) {
        System.out.print("Введите ID члена для удаления: ");
        int id = getValidInteger(scanner);
        memberDAO.deleteMember(id);
        System.out.println("Член успешно удален!");
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

    private LocalDate getValidDate(Scanner scanner) {
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Ошибка: неверный формат даты. Пожалуйста, используйте формат YYYY-MM-DD: ");
            }
        }
    }
}