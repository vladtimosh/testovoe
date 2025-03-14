package org.example.model;

public class Genre {
    private final int id; // genre_id
    private String genreName; // genre_name

    public Genre(int id, String genreName) {
        this.id = id;
        setGenreName(genreName);
    }

    // Конструктор без параметров
    public Genre() {
        this.id = 0; // или другое значение по умолчанию
        this.genreName = ""; // или другое значение по умолчанию
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    // Сеттер для genreName с валидацией
    public void setGenreName(String genreName) {
        if (genreName == null || genreName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название жанра не может быть пустым.");
        }
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{id=" + id + ", genreName='" + genreName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        return genreName.equals(genre.genreName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + genreName.hashCode();
        return result;
    }
}