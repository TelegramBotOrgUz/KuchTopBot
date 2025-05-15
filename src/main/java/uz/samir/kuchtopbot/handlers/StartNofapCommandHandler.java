package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.modelService.UserService;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class StartNofapCommandHandler {

    private final UserStateService userStateService;
    private final UserService userService;
    private final MainMenuHandler mainMenuHandler;
    private final MessageService messageService;
    private final TelegramBotMessageController telegramBotMessageController;

    public void handleStartNofap(Long chatId) {
        userService.startNofap(chatId);
        User user = userService.getUser(chatId);
        userStateService.saveState(chatId, BotState.MAIN_MENU.name());
        String language = userStateService.getLanguage(chatId);
        Locale locale = new Locale(language);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("d MMMM yyyy", locale); // Masalan: 14 may 2025 yoki 14 мая 2025

        String formattedDate = user.getNofapStartedAt().format(formatter);
        String message = String.format(
                messageService.getMessage(chatId, "nofap_started_message"),
                formattedDate
        );
        telegramBotMessageController.sendMessage(chatId, message);
        mainMenuHandler.showMainMenu(chatId);
    }
}