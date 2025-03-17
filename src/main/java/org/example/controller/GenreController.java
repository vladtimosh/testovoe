package org.example.controller;

import org.example.model.Genre;
import org.example.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    // Получение всех жанров
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Получение жанра по ID
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return genreRepository.findById(id)
                .map(genre -> ResponseEntity.ok().body(genre))
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового жанра
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre createdGenre = genreRepository.save(genre);
        return ResponseEntity.ok(createdGenre); // Возвращаем созданный жанр
    }

    // Обновление информации о жанре
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genre.setGenreName(genreDetails.getGenreName());
                    Genre updatedGenre = genreRepository.save(genre);
                    return ResponseEntity.ok(updatedGenre);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление жанра по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable Long id) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genreRepository.delete(genre);
                    return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
                })
                .orElse(ResponseEntity.notFound().build()); // Возвращаем 404, если не найдено
    }
}