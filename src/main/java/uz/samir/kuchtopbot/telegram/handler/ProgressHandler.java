package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;

@Component
@RequiredArgsConstructor
public class ProgressHandler {

    private final TelegramBotMessageController messageController;
    private final UserService userService;
    private final MessageService messageService;

    public void handleProgress(Long chatId) {
        // Foydalanuvchi progressini ko‘rsatish
        String progressMessage = messageService.getMessage(chatId, "progress_message");
        messageController.sendMessage(chatId, progressMessage);
    }
}