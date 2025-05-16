
package uz.samir.kuchtopbot.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.event.BotMessageEvent;
import uz.samir.kuchtopbot.service.bot.TelegramBotService;

@Component
@RequiredArgsConstructor
public class BotMessageEventListener implements ApplicationListener<BotMessageEvent> {

    private final TelegramBotService telegramBotService;

    @Override
    public void onApplicationEvent(BotMessageEvent event) {
        long chatId = event.getChatId();
        String text = event.getText();
        ReplyKeyboard replyMarkup = event.getReplyKeyboard();

        SendMessage sendMessage = new SendMessage(Long.toString(chatId), text);
        if (replyMarkup != null) {
            sendMessage.setReplyMarkup(replyMarkup);
        }
        telegramBotService.sendMessage(sendMessage);
    }
}