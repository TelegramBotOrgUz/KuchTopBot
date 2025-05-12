package uz.samir.kuchtopbot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.BotService;
import uz.samir.kuchtopbot.service.UserService;

@Component
@RequiredArgsConstructor
public class WillUpBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final UserService userService;
    private final BotService botService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if ("/start".equals(messageText)) {
                User user = userService.registerIfNotExists(update.getMessage().getFrom(), chatId);
                SendMessage message = new SendMessage(chatId.toString(),
                        "👋 Assalomu alaykum, " + user.getFirstName() + "! KuchTopBot'ga xush kelibsiz.\n\n🚀 Bugundan boshlab sizning NoFap yo‘lingizni birga yuramiz.");
                try {
                    execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            String response = botService.handleMessage(update.getMessage());
            SendMessage message = new SendMessage(chatId.toString(), response);
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}