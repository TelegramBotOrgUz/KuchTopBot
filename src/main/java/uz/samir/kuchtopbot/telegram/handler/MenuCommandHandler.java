package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.cache.UserStateService;

@Component
@RequiredArgsConstructor
public class MenuCommandHandler {

    private final MessageService messages;
    private final TelegramBotMessageController telegramBotMessageController;
    private final UserStateService userStateService;


    public void showMenu(long chatId){
        telegramBotMessageController.sendMessage(chatId,messages.getMessage(chatId,"choose"));
    }

}
