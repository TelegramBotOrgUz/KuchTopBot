package uz.samir.kuchtopbot.service.modelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StreakService streakService;

    public User registerIfNotExists(org.telegram.telegrambots.meta.api.objects.User telegramUser, Long chatId) {
        return userRepository.findByChatId(chatId).orElseGet(() -> {
            User user = new User();
            user.setChatId(chatId);
            user.setUsername(telegramUser.getUserName());
            user.setFirstName(telegramUser.getFirstName());
            user.setLastName(telegramUser.getLastName());
            user.setRegisteredAt(LocalDateTime.now());
            user.setActive(true);
            user.setStreak(0);
            return userRepository.save(user);
        });
    }

    public boolean isNofapActive(Long chatId) {
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        return optionalUser.map(user -> user.getNofapStartedAt() != null).orElse(false);
    }

    public void resetStreak(Long chatId) {
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        optionalUser.ifPresent(user -> {
            user.setStreak(0); // Reset the streak
            user.setNofapStartedAt(null); // Clear the NoFap start date
            userRepository.save(user); // Save changes to the database
        });
    }

    public void startNofap(Long chatId) {
        userRepository.findByChatId(chatId).ifPresent(user -> {
            user.setNofapStartedAt(LocalDateTime.now());
            userRepository.save(user);
            streakService.startNewStreak(user.getId());
        });
    }

    public User getUser(Long chatId) {
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        return optionalUser.orElse(null);
    }

    public Optional<Streak> getActiveStreak(Long userId) {
        return streakService.getActiveStreak(userId);
    }


}
