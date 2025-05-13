package uz.samir.kuchtopbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import uz.samir.kuchtopbot.event.BotDeleteEvent;
import uz.samir.kuchtopbot.event.BotMessageEvent;


@Service
@RequiredArgsConstructor
public class TelegramBotMessageController {

    private final ApplicationEventPublisher eventPublisher;

    public void sendMessage(long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(long chatId, String text, ReplyKeyboard replyMarkup) {
        eventPublisher.publishEvent(new BotMessageEvent(this, chatId, text, replyMarkup));
    }

    public void deleteMessage(long chatId, int messageId) {
        eventPublisher.publishEvent(new BotDeleteEvent(this, chatId, messageId));
    }

}
