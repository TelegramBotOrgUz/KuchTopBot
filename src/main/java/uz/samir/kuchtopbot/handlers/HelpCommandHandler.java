package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;

@Component
@RequiredArgsConstructor
public class HelpCommandHandler {

    private final TelegramBotMessageController messageController;
    private final MessageService messageService;
    private final UserService userService;

    public void handle(Long chatId) {
        messageController
                .sendMessage(chatId,
                        messageService.getMessage(chatId, "help_message"));
    }
}
