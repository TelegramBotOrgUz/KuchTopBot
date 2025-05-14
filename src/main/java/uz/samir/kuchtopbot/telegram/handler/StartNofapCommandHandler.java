package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.buttons.KeyboardUtils;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.template.BotState;

@Component
@RequiredArgsConstructor
public class StartNofapCommandHandler {

    private final UserStateService userStateService;
    private final TelegramBotMessageController messageController;
    private final MessageService messages;
    private final KeyboardUtils keyboardUtils;

    public void handleStartNofap(Long chatId) {
        // Foydalanuvchining NoFap journey'ini boshlash
        userStateService.saveState(chatId, BotState.START_NOFAP.name());
        messageController.sendMessage(chatId, messages.getMessage(chatId, "start_nofap_message"),
                keyboardUtils.getUserMenuKeyboard(chatId));
    }
}