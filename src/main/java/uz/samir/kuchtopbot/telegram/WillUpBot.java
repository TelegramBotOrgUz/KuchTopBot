package uz.samir.kuchtopbot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.handlers.*;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;
import uz.samir.kuchtopbot.service.cache.UserStateService;

@Component
@RequiredArgsConstructor
public class WillUpBot extends TelegramLongPollingBot {

    private final StartNofapCommandHandler startNofapCommandHandler;
    private final MessageService messageService;
    private final AdminMotivationHandler adminMotivationHandler;
    private final TelegramBotMessageController telegramBotMessageController;
    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final UserService userService;
    private final StartCommandHandler startCommandHandler;
    private final UserStateService userStateService;
    private final MainMenuHandler mainMenuHandler;
    private final ProgressHandler progressHandler;
    private final MotivationHandler motivationHandler;
    private final ResetHandler resetHandler;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
//            Integer messageId = update.getMessage().getMessageId();


            if ("/start".equals(messageText)) {
                startCommandHandler.startUserCommand(chatId);
                userStateService.saveState(chatId, BotState.CHOOSE_LANGUAGE.name());
            } else if (messageText.startsWith("/addmotivation")) {
                adminMotivationHandler.handleAddMotivation(chatId, messageText);
            } else if (userStateService.getState(chatId).equals(BotState.CHOOSE_LANGUAGE.name())) {
                User user = userService.registerIfNotExists(update.getMessage().getFrom(), chatId);
                startCommandHandler.languageCommand(chatId, messageText, user);
            } else if (userStateService.getState(chatId).equals(BotState.START_NOFAP.name()) &&
                    messageText.equals(messageService.getMessage(chatId, "start_nofap"))) {
                startNofapCommandHandler.handleStartNofap(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_progress"))) {
                progressHandler.handleStats(chatId);
                progressHandler.showRelapseStats(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_motivation"))) {
                motivationHandler.handleMotivation(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_reset"))) {
                resetHandler.askForRelapseReason(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_settings"))) {
                telegramBotMessageController.sendMessage(chatId, messageService.getMessage(chatId, "choose_language"));
                startCommandHandler.startUserCommand(chatId);
                userStateService.saveState(chatId, BotState.CHOOSE_LANGUAGE.name());
            } else if (userStateService.getState(chatId).equals(BotState.REASON_INPUT.name())) {
                resetHandler.saveReset(chatId, messageText);
            }
        }
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackData = callbackQuery.getData();
            Long chatId = callbackQuery.getMessage().getChatId();

            int messageId = callbackQuery.getMessage().getMessageId();

            telegramBotMessageController.deleteMessage(chatId, messageId - 1);
            telegramBotMessageController.deleteMessage(chatId, messageId);
            switch (callbackData) {
                case "no_reason" -> resetHandler.saveReset(chatId,
                        messageService.getMessage(chatId, "relapse_default_note"));
                case "write_reason" -> resetHandler.handleResetReason(chatId);
                case "cancel" -> mainMenuHandler.showMainMenu(chatId);
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