package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.samir.kuchtopbot.buttons.KeyboardUtils;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MainMenuHandler {

    private final UserService userService;
    private final MessageService messageService;
    private final TelegramBotMessageController messageController;

    public void showMainMenu(Long chatId) {
        ReplyKeyboardMarkup menu = userService.isNofapActive(chatId)
                ? KeyboardUtils.getActiveNofapMenu(messageService, chatId)
                : KeyboardUtils.getPreStartMenu(messageService, chatId);

        String title = messageService.getMessage(chatId, "main_menu_title");
        messageController.sendMessage(chatId, title, menu);
    }

    public void showMainMenuAll(long chatId) {
        String title = messageService.getMessage(chatId, "main_menu_title");
        messageController.sendMessage(chatId, title, KeyboardUtils.getReplyKeyboardMarkupAll(messageService, chatId));
    }

    public void handleRelapseSuccess(Long chatId) {
        User user = userService.getUser(chatId);
        Optional<Streak> activeStreak = userService.getActiveStreak(user.getId());

        if (activeStreak.isPresent()) {
            long days = java.time.Duration.between(activeStreak.get().getStartedAt(), java.time.LocalDateTime.now()).toDays();

            StringBuilder msg = new StringBuilder();
            msg.append("🔥 ").append(messageService.getMessage(chatId, "relapse_no_message")).append("\n");
            msg.append("📅 ").append(messageService.getMessage(chatId, "nofap_current_days")).append(": ").append(days).append(" ✅");

            if (days == 3 || days == 7 || days == 14 || days == 30 || days == 60 || days == 90) {
                msg.append("\n\n🎉 ").append(messageService.getMessage(chatId, "milestone_reached_" + days));
            }

            messageController.sendMessage(chatId, msg.toString());
        }

        showMainMenu(chatId);
    }
}