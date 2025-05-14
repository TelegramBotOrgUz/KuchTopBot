package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.payload.UserStatsDto;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.StreakService;
import uz.samir.kuchtopbot.service.modelService.UserService;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ProgressHandler {

    private final StreakService streakService;
    private final MessageService messageService;
    private final TelegramBotMessageController messageController;

    public void handleStats(Long chatId) {
        UserStatsDto stats = streakService.getStats(chatId);

        String formattedStart = stats.getNofapStartedAt() != null
                ? stats.getNofapStartedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                : "-";

        String formattedRelapse = stats.getLastRelapseAt() != null
                ? stats.getLastRelapseAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                : "-";

        String msg = String.format(
                messageService.getMessage(chatId, "stats_message"),
                stats.getCurrentStreakDays(),
                formattedStart,
                stats.getBestStreakDays(),
                formattedRelapse
        );

        messageController.sendMessage(chatId, msg);
    }
}