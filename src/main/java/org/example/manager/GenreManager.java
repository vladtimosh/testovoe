package manager;

import dao.GenreDAO;
import model.Genre;

import java.util.List;
import java.util.Scanner;

public class GenreManager {
    private GenreDAO genreDAO;

    public GenreManager(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public void manageGenres(Scanner scanner) {
        int genreChoice;

        do {
            System.out.println("\n--- Управление жанрами ---");
            System.out.println("1. Добавить жанр");
            System.out.println("2. Просмотреть жанр");
            System.out.println("3. Просмотреть все жанры");
            System.out.println("4. Обновить жанр");
            System.out.println("5. Удалить жанр");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");
            genreChoice = scanner.nextInt();

            switch (genreChoice) {
                case 1:
                    addGenre(scanner);
                    break;
                case 2:
                    viewGenre(scanner);
                    break;
                case 3:
                    viewAllGenres();
                    break;
                case 4:
                    updateGenre(scanner);
                    break;
                case 5:
                    deleteGenre(scanner);
                    break;
                case 6:
                    System.out.println("Возвращение в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (genreChoice != 6);
    }

    private void addGenre(Scanner scanner) {
        System.out.print("Введите название жанра: ");
        String name = scanner.next();

        Genre genre = new Genre(0, name);
        genreDAO.insertGenre(genre);
        System.out.println("Жанр успешно добавлен!");
    }

    private void viewGenre(Scanner scanner) {
        System.out.print("Введите ID жанра: ");
        int id = scanner.nextInt();
        Genre genre = genreDAO.getGenre(id);
        if (genre != null) {
            System.out.println(genre);
        } else {
            System.out.println("Жанр не найден.");
        }
    }

    private void viewAllGenres() {
        List<Genre> genres = genreDAO.getAllGenres();
        if (genres.isEmpty()) {
            System.out.println("Нет доступных жанров.");
        } else {
            for (Genre genre : genres) {
                System.out.println(genre);
            }
        }
    }

    private void updateGenre(Scanner scanner) {
        System.out.print("Введите ID жанра для обновления: ");
        int id = scanner.nextInt();
        Genre genre = genreDAO.getGenre(id);
        if (genre != null) {
            System.out.print("Введите новое название жанра: ");
            String newName = scanner.next();

            genre.setName(newName);
            genreDAO.updateGenre(genre);
            System.out.println("Жанр успешно обновлен!");
        } else {
            System.out.println("Жанр не найден.");
        }
    }

    private void deleteGenre(Scanner scanner) {
        System.out.print("Введите ID жанра для удаления: ");
        int id = scanner.nextInt();
        genreDAO.deleteGenre(id);
        System.out.println("Жанр успешно удален!");
    }
}