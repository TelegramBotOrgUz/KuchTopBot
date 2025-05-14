package uz.samir.kuchtopbot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.BotService;
import uz.samir.kuchtopbot.service.UserService;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.telegram.handler.StartCommandHandler;

@Component
@RequiredArgsConstructor
public class WillUpBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final UserService userService;
    private final BotService botService;
    private final StartCommandHandler startCommandHandler;
    private final TelegramBotMessageController messageController;
    private final UserStateService userStateService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if ("/start".equals(messageText)) {
                startCommandHandler.startUserCommand(chatId);
                userStateService.saveState(chatId, BotState.CHOOSE_LANGUAGE.name());
            } else if (userStateService.getState(chatId).equals(BotState.CHOOSE_LANGUAGE.name())) {
                User user = userService.registerIfNotExists(update.getMessage().getFrom(), chatId);
                startCommandHandler.languageCommand(chatId, messageText,user);
            }

        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}