package org.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private int id;
    private String title;
    private LocalDate publicationDate;
    private int genreId; // Поле genreId

    // Конструктор с четырьмя параметрами
    public Book(int id, String title, LocalDate publicationDate, int genreId) {
        setId(id);
        setTitle(title);
        setPublicationDate(publicationDate);
        setGenreId(genreId);
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
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        if (publicationDate == null || publicationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Дата публикации не может быть в будущем.");
        }
        this.publicationDate = publicationDate;
    }

    public int getGenreId() { // Геттер для genreId
        return genreId;
    }

    public void setGenreId(int genreId) { // Сеттер для genreId
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationDate=" + (publicationDate != null ? publicationDate.format(formatter) : "неизвестна") +
                ", genreId=" + genreId +
                '}';
    }
}