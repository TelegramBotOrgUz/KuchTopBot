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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RelapseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Noyob ID

    private Long userId; // Relaps qilgan foydalanuvchi IDsi

    private Long streakId; // Ushbu relaps qaysi streakga tegishli

    private LocalDateTime relapsedAt; // Relaps qilingan aniq vaqt

    private String note; // Foydalanuvchidan izoh (ixtiyoriy)
}
