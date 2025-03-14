package org.example.model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private int memberId;
    private int bookId; // Добавляем поле bookId
    private LocalDate loanDate;
    private LocalDate returnDate;

    // Конструктор
    public Loan(int id, int memberId, int bookId, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId; // Инициализация bookId
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() { // Геттер для bookId
        return bookId;
    }

    public void setBookId(int bookId) { // Сеттер для bookId
        this.bookId = bookId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}