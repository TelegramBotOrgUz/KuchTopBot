package uz.samir.kuchtopbot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatsDto {
    private int currentStreakDays;
    private int bestStreakDays;
    private LocalDateTime nofapStartedAt;
    private LocalDateTime lastRelapseAt;
}
