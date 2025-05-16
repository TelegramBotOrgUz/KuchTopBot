package uz.samir.kuchtopbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import uz.samir.kuchtopbot.event.BotDeleteEvent;
import uz.samir.kuchtopbot.event.BotMessageEvent;
import uz.samir.kuchtopbot.telegram.WillUpBot;


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
