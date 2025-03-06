package model;

import java.util.Date;

public class Loan {
    private int id;
    private int bookId;
    private int memberId; // Изменено на memberId
    private Date loanDate;
    private Date returnDate;

    // Конструктор
    public Loan(int id, int bookId, int memberId, Date loanDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId; // Инициализация memberId
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() { // Изменено на getMemberId
        return memberId;
    }

    public void setMemberId(int memberId) { // Изменено на setMemberId
        this.memberId = memberId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{id=" + id + ", bookId=" + bookId + ", memberId=" + memberId + // Изменено на memberId
                ", loanDate=" + loanDate + ", returnDate=" + returnDate + '}';
    }
}