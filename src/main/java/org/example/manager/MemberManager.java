package manager;

import dao.MemberDAO;
import model.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    private MemberDAO memberDAO;

    public MemberManager(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void manageMembers(Scanner scanner) {
        int memberChoice;

        do {
            System.out.println("\n--- Управление членами ---");
            System.out.println("1. Добавить члена");
            System.out.println("2. Просмотреть члена");
            System.out.println("3. Просмотреть всех членов");
            System.out.println("4. Обновить члена");
            System.out.println("5. Удалить члена");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");
            memberChoice = scanner.nextInt();

            switch (memberChoice) {
                case 1:
                    addMember(scanner);
                    break;
                case 2:
                    viewMember(scanner);
                    break;
                case 3:
                    viewAllMembers();
                    break;
                case 4:
                    updateMember(scanner);
                    break;
                case 5:
                    deleteMember(scanner);
                    break;
                case 6:
                    System.out.println("Возвращение в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (memberChoice != 6);
    }

    private void addMember(Scanner scanner) {
        System.out.print("Введите полное имя члена: ");
        String fullName = scanner.next();
        System.out.print("Введите дату членства (YYYY-MM-DD): ");
        LocalDate membershipDate = LocalDate.parse(scanner.next());

        Member member = new Member(0, fullName, membershipDate);
        memberDAO.insertMember(member);
        System.out.println("Член успешно добавлен!");
    }

    private void viewMember(Scanner scanner) {
        System.out.print("Введите ID члена: ");
        int id = scanner.nextInt();
        Member member = memberDAO.getMember(id);
        if (member != null) {
            System.out.println(member);
        } else {
            System.out.println("Член не найден.");
        }
    }

    private void viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("Нет доступных членов.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    private void updateMember(Scanner scanner) {
        System.out.print("Введите ID члена для обновления: ");
        int id = scanner.nextInt();
        Member member = memberDAO.getMember(id);
        if (member != null) {
            System.out.print("Введите новое полное имя члена: ");
            String newFullName = scanner.next();
            System.out.print("Введите новую дату членства (YYYY-MM-DD): ");
            LocalDate newMembershipDate = LocalDate.parse(scanner.next());

            member.setFullName(newFullName);
            member.setMembershipDate(newMembershipDate); // Убедитесь, что есть соответствующий сеттер
            memberDAO.updateMember(member);
            System.out.println("Член успешно обновлен!");
        } else {
            System.out.println("Член не найден.");
        }
    }

    private void deleteMember(Scanner scanner) {
        System.out.print("Введите ID члена для удаления: ");
        int id = scanner.nextInt();
        memberDAO.deleteMember(id);
        System.out.println("Член успешно удален!");
    }
}