package uz.samir.kuchtopbot.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.samir.kuchtopbot.service.MessageService;
import uz.samir.kuchtopbot.service.UserService;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommandHandler {

    private final UserService userService;

    private SendMessage sendLanguageSelection(Long chatId) {
        KeyboardRow row = new KeyboardRow();
        row.add("🇺🇿 O‘zbekcha");
        row.add("🇷🇺 Русский");
        row.add("🇬🇧 English");

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(List.of(row));
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Iltimos, tilni tanlang:")
                .replyMarkup(markup)
                .build();
    }
}
