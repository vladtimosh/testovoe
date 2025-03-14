package org.example.model;

import java.time.LocalDate;

public class Member {
    private int id; // Идентификатор члена
    private String fullName; // Полное имя члена
    private LocalDate membershipDate; // Дата членства

    // Конструктор
    public Member(int id, String fullName, LocalDate membershipDate) {
        this.id = id;
        this.fullName = fullName; // Используем полное имя
        this.membershipDate = membershipDate;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() { // Геттер для полного имени
        return fullName;
    }

    public void setFullName(String fullName) { // Сеттер для полного имени
        this.fullName = fullName;
    }

    public LocalDate getMembershipDate() { // Геттер для даты членства
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) { // Сеттер для даты членства
        this.membershipDate = membershipDate;
    }

    @Override
    public String toString() {
        return "Member{id=" + id + ", fullName='" + fullName + '\'' +
                ", membershipDate=" + membershipDate + '}';
    }
}