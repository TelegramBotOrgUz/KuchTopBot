package uz.samir.kuchtopbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id; // Foydalanuvchini noyob IDsi (DB uchun)

    private Long chatId; // Telegram chat ID (foydalanuvchini aniqlash uchun)
    private String username; // Telegram username (agar mavjud bo‘lsa)
    private String firstName; // Foydalanuvchining ismi
    private String lastName; // Foydalanuvchining familiyasi (agar mavjud bo‘lsa)

    private LocalDateTime registeredAt; // Botga birinchi marta kirgan vaqti
    private LocalDateTime lastCheckIn; // Oxirgi kunlik belgilash (check-in) vaqti

    private int streak; // Hozirgi ketma-ket kunlar soni
    private boolean active; // Foydalanuvchi botdan foydalanayotgan holatdami

    private LocalDateTime nofapStartedAt; // NoFap journey qachon boshlangan
}
