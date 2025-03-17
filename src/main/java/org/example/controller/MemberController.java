package org.example.controller;

import org.example.model.Member;
import org.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    // Получение всех членов
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Получение члена по ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> ResponseEntity.ok().body(member))
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового члена
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberRepository.save(member);
        return ResponseEntity.ok(createdMember); // Возвращаем созданного члена
    }

    // Обновление информации о члене
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setFullName(memberDetails.getFullName());
                    member.setMembershipDate(memberDetails.getMembershipDate());
                    Member updatedMember = memberRepository.save(member);
                    return ResponseEntity.ok(updatedMember);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление члена по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
                })
                .orElse(ResponseEntity.notFound().build()); // Возвращаем 404, если не найдено
    }
}