package uz.samir.kuchtopbot.service.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.service.modelService.MotivationService;
import uz.samir.kuchtopbot.service.modelService.StreakService;
import uz.samir.kuchtopbot.service.modelService.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MotivationScheduler {

    private final UserService userService;
    private final StreakService streakService;
    private final MotivationService motivationService;
    private final TelegramBotMessageController messageController;
    private final UserStateService userStateService;

    @Scheduled(cron = "0 0 8 * * *") // Har kuni ertalab soat 08:00
    public void sendDailyMotivations() {
        List<User> activeUsers = userService.getAllWithActiveStreak(); // Faol foydalanuvchilar

        for (User user : activeUsers) {
            String motivation = motivationService.getRandomMotivation(userStateService.getLanguage(user.getChatId()));
            String message = "💬 Bugungi motivatsiya:\n\n" + motivation;
            messageController.sendMessage(user.getChatId(), message);
        }
    }
}
