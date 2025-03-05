package model;

import java.util.Date;

public class Loan {
    private int id;
    private int bookId;
    private int userId;
    private Date loanDate;
    private Date returnDate;

    public Loan(int id, int bookId, int userId, Date loanDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "Loan{id=" + id + ", bookId=" + bookId + ", userId=" + userId +
                ", loanDate=" + loanDate + ", returnDate=" + returnDate + '}';
    }
}