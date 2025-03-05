package model;

public class Book {
    private int id;
    private String title;
    private String publishedDate;
    private int genreId;

    public Book(int id, String title, String publishedDate, int genreId) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.genreId = genreId;
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
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
                ", publishedDate='" + publishedDate + '\'' +
                ", genreId=" + genreId + '}';
    }
}