package uz.samir.kuchtopbot.service.modelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.repository.StreakRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;

    public void startNewStreak(Long userId) {
        // Avvalgi streak tugatilmagan bo‘lsa, uni tugatamiz
        streakRepository.findByUserIdAndEndedAtIsNull(userId).ifPresent(old -> {
            old.setEndedAt(LocalDateTime.now());
            old.setRelapsed(true);
            old.setDurationDays((int) Duration.between(old.getStartedAt(), old.getEndedAt()).toDays());
            streakRepository.save(old);
        });

        // Yangi streak boshlaymiz
        Streak streak = new Streak();
        streak.setUserId(userId);
        streak.setStartedAt(LocalDateTime.now());
        streak.setRelapsed(false);
        streakRepository.save(streak);
    }
}