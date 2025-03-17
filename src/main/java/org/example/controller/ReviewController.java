package org.example.controller;

import org.example.model.Review;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    // Получение всех отзывов
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Получение отзыва по ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> ResponseEntity.ok().body(review))
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового отзыва
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewRepository.save(review);
        return ResponseEntity.ok(createdReview); // Возвращаем созданный отзыв
    }

    // Обновление информации об отзыве
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setBook(reviewDetails.getBook());
                    review.setMember(reviewDetails.getMember());
                    review.setRating(reviewDetails.getRating());
                    review.setComment(reviewDetails.getComment());
                    Review updatedReview = reviewRepository.save(review);
                    return ResponseEntity.ok(updatedReview);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление отзыва по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
                })
                .orElse(ResponseEntity.notFound().build()); // Возвращаем 404, если не найдено
    }
}