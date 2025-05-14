package uz.samir.kuchtopbot.service.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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
