package uz.samir.kuchtopbot.service.modelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.payload.UserStatsDto;
import uz.samir.kuchtopbot.repository.StreakRepository;
import uz.samir.kuchtopbot.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;
    private final UserRepository userRepository;

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

    public UserStatsDto getStats(Long chatId) {
        User user = userRepository.findByChatId(chatId).orElseThrow();

        int best = streakRepository.findBestStreak(user.getId()).orElse(0);

        int current = 0;
        LocalDateTime start = null;
        Optional<Streak> currentStreak = streakRepository.findByUserIdAndEndedAtIsNull(user.getId());
        if (currentStreak.isPresent()) {
            start = currentStreak.get().getStartedAt();
            current = (int) Duration.between(start, LocalDateTime.now()).toDays();
        }

        LocalDateTime lastRelapse = streakRepository.findLastRelapse(user.getId())
                .map(Streak::getEndedAt)
                .orElse(null);

        return new UserStatsDto(current, best, start, lastRelapse);
    }
}