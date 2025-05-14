package uz.samir.kuchtopbot.telegram.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;

@Component
@RequiredArgsConstructor
public class MotivationHandler {

    private final TelegramBotMessageController messageController;
    private final MessageService messageService;

    public void handleMotivation(Long chatId) {
        // Kunlik motivatsiya yuborish
        String motivationMessage = messageService.getMessage(chatId, "daily_motivation_message");
        messageController.sendMessage(chatId, motivationMessage);
    }
}
