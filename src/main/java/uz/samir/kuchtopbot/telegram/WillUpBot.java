package uz.samir.kuchtopbot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.bot.BotService;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.telegram.handler.StartCommandHandler;
import uz.samir.kuchtopbot.telegram.handler.StartNofapCommandHandler;
import uz.samir.kuchtopbot.telegram.handler.MainMenuHandler;
import uz.samir.kuchtopbot.telegram.handler.ProgressHandler;
import uz.samir.kuchtopbot.telegram.handler.MotivationHandler;
import uz.samir.kuchtopbot.telegram.handler.ResetHandler;

@Component
@RequiredArgsConstructor
public class WillUpBot extends TelegramLongPollingBot {

    private final StartNofapCommandHandler startNofapCommandHandler;
    private final MessageService messageService;
    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final UserService userService;
    private final BotService botService;
    private final StartCommandHandler startCommandHandler;
    private final TelegramBotMessageController messageController;
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

            // /start komandasini tahlil qilish
            if ("/start".equals(messageText)) {
                startCommandHandler.startUserCommand(chatId);
                userStateService.saveState(chatId, BotState.CHOOSE_LANGUAGE.name());
            }
            // Til tanlash jarayoni
            else if (userStateService.getState(chatId).equals(BotState.CHOOSE_LANGUAGE.name())) {
                User user = userService.registerIfNotExists(update.getMessage().getFrom(), chatId);
                startCommandHandler.languageCommand(chatId, messageText, user);
                mainMenuHandler.showMainMenu(chatId);
            }
            // NoFap boshlash jarayoni
            else if (userStateService.getState(chatId).equals(BotState.START_NOFAP.name()) &&
                    messageText.equals(messageService.getMessage(chatId, "start_nofap"))) {
                startNofapCommandHandler.handleStartNofap(chatId);
            }
            // Asosiy menyu va boshqa handlerlar
            else if (userStateService.getState(chatId).equals(BotState.MAIN_MENU.name())) {

            }
            // Progress, motivatsiya va reset handlerlarini chaqirish
            else if (messageText.equals(messageService.getMessage(chatId, "menu_progress"))) {
                progressHandler.handleProgress(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_motivation"))) {
                motivationHandler.handleMotivation(chatId);
            } else if (messageText.equals(messageService.getMessage(chatId, "menu_reset"))) {
                resetHandler.handleReset(chatId);
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