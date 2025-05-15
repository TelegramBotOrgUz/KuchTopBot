package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.samir.kuchtopbot.buttons.KeyboardUtils;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;

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
        messageController.sendMessage(chatId,title,KeyboardUtils.getReplyKeyboardMarkupAll(messageService, chatId));
    }
}