package model;

import java.time.LocalDate;

public class Member {
    private int id;
    private String fullName; // Полное имя
    private LocalDate membershipDate; // Дата членства

    public Member(int id, String fullName, LocalDate membershipDate) {
        this.id = id;
        this.fullName = fullName;
        this.membershipDate = membershipDate;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) { // Добавлен метод setFullName
        this.fullName = fullName;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) { // Добавлен метод setMembershipDate
        this.membershipDate = membershipDate;
    }

    @Override
    public String toString() {
        return "Member{id=" + id + ", fullName='" + fullName + '\'' + ", membershipDate=" + membershipDate + '}';
    }
}