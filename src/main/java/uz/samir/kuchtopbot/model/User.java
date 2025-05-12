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
    private Long id;
    private Long chatId;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime registeredAt;
    private LocalDateTime lastRelapseAt;
    private LocalDateTime lastCheckIn;
    private int streak;
    private boolean active;

    // Qo‘shimcha: rekord ketma-ket kunlar
    private int currentStreak;
    private int bestStreak;
}
