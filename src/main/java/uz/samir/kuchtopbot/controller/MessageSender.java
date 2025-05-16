package uz.samir.kuchtopbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.samir.kuchtopbot.telegram.WillUpBot;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final WillUpBot bot;

    public Message sendMessageWithResult(Long chatId, String text, InlineKeyboardMarkup markup) {
        SendMessage message = new SendMessage(chatId.toString(), text);
        message.setReplyMarkup(markup);
        try {
            return bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
