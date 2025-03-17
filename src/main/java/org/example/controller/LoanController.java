package org.example.controller;

import org.example.model.Loan;
import org.example.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;

    // Получение всех займов
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Получение займа по ID
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return loanRepository.findById(id)
                .map(loan -> ResponseEntity.ok().body(loan))
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового займа
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanRepository.save(loan);
        return ResponseEntity.ok(createdLoan); // Возвращаем созданный займ
    }

    // Обновление информации о займе
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setBook(loanDetails.getBook());
                    loan.setMember(loanDetails.getMember());
                    loan.setLoanDate(loanDetails.getLoanDate());
                    loan.setReturnDate(loanDetails.getReturnDate());
                    Loan updatedLoan = loanRepository.save(loan);
                    return ResponseEntity.ok(updatedLoan);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление займа по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoan(@PathVariable Long id) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loanRepository.delete(loan);
                    return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
                })
                .orElse(ResponseEntity.notFound().build()); // Возвращаем 404, если не найдено
    }
}