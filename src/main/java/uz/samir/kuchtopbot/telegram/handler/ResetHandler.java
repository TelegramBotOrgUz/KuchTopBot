package uz.samir.kuchtopbot.telegram.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;

@Component
@RequiredArgsConstructor
public class ResetHandler {

    private final TelegramBotMessageController messageController;
    private final UserService userService;
    private final MessageService messageService;

    public void handleReset(Long chatId) {
        // Foydalanuvchining NoFap progressini reset qilish
        userService.resetStreak(chatId);
        String resetMessage = messageService.getMessage(chatId, "reset_message");
        messageController.sendMessage(chatId, resetMessage);
    }
}
