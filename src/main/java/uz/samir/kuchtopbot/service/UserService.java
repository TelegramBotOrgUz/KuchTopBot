package uz.samir.kuchtopbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
            user.setCurrentStreak(0);
            user.setBestStreak(0);
            return userRepository.save(user);
        });
    }
}
