package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.buttons.KeyboardUtils;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.cache.UserStateService;


@Component
@RequiredArgsConstructor
public class StartCommandHandler {

    private final MessageService messages;
    private final TelegramBotMessageController telegramBotMessageController;
    private final UserStateService userStateService;

    public void startUserCommand(long chatId) {
        String text = """
                🇺🇿 Tilni tanlang
                🇷🇺 Выберите язык
                🇬🇧 Select a language
                """;
        telegramBotMessageController.sendMessage(chatId, text, KeyboardUtils.getLanguageKeyboard());
    }

    public void languageCommand(Long chatId, String messageText, User user) {
        String lang;
        switch (messageText) {
            case "🇬🇧 English" -> lang = "en";
            case "🇷🇺 Русский" -> lang = "ru";
            default -> lang = "uz";
        }
        userStateService.saveLanguage(chatId, lang);
        String message = String.format(messages.getMessage(chatId, "welcome_message"), user.getFirstName()) +
                "\n" +
                messages.getMessage(chatId, "start_nofap_message");

        telegramBotMessageController.sendMessage(chatId, message);
        userStateService.saveState(chatId, BotState.START_NOFAP.name());
    }
}
