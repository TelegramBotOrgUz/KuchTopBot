package uz.samir.kuchtopbot.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import uz.samir.kuchtopbot.telegram.WillUpBot;

@Component
@RequiredArgsConstructor
public class BotDeleteEventListener {

    private final WillUpBot bot; // TelegramLongPollingBot

    @EventListener
    public void handleDelete(BotDeleteEvent event) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(String.valueOf(event.getChatId()));
        deleteMessage.setMessageId(event.getMessageId());

        try {
            bot.execute(deleteMessage);
        } catch (Exception e) {
            e.printStackTrace(); // yoki logger
        }
    }
}
