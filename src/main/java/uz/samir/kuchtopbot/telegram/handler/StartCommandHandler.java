package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.samir.kuchtopbot.buttons.KeyboardUtils;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.MessageService;
import uz.samir.kuchtopbot.service.TelegramBotService;
import uz.samir.kuchtopbot.service.UserService;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommandHandler {

    private final MessageService messages;
    private final TelegramBotMessageController telegramBotMessageController;

    public void startUserCommand(long chatId) {
        telegramBotMessageController.sendMessage(chatId, messages.getMessage(chatId, "choose_language"), KeyboardUtils.getLanguageKeyboard());
    }
}
