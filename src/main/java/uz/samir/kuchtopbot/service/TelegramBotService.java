package uz.samir.kuchtopbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service
public class TelegramBotService {
    private final TelegramLongPollingBot bot;

    public TelegramBotService(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public void sendMessage(SendMessage message) {
        try {
            bot.execute(message);  // Telegram API orqali xabar yuborish
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
