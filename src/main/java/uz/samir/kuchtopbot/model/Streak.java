package uz.samir.kuchtopbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Har bir streak uchun noyob identifikator

    private Long userId; // Bu streakni egallagan foydalanuvchi IDsi

    private LocalDateTime startedAt; // Streak boshlangan vaqt

    private LocalDateTime endedAt; // Streak tugagan vaqt; agar null bo‘lsa, hozir ham davom etmoqda

    private boolean relapsed; // Agar foydalanuvchi streakni sindi deb belgilasa

    // Qo‘shimcha statistikalar uchun
    private int durationDays; // (faqat endedAt mavjud bo‘lsa, hisoblanadi)
}
