package model;

import java.sql.Date; // Импортируем java.sql.Date

public class Book {
    private int id;
    private String title;
    private Date publishedDate; // Используем java.sql.Date
    private int genreId;

    // Конструктор
    public Book(int id, String title, Date publishedDate, int genreId) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.genreId = genreId; // Присваиваем значение genreId
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + '\'' +
                ", publishedDate=" + publishedDate + // Изменен формат вывода
                ", genreId=" + genreId + '}';
    }
}