package uz.samir.kuchtopbot.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InlineKeyboardUtil {

    private final TelegramBotMessageController messageController;

    public  InlineKeyboardMarkup askForRelapseReason() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Sababsiz");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Sababni yozish");

        button1.setCallbackData("no_reason"); // Sababsiz tugma
        button2.setCallbackData("write_reason"); // Sabab yozish tugmasi

        List<InlineKeyboardButton> row1 = List.of(button1);
        List<InlineKeyboardButton> row2 = List.of(button2);
        keyboardMarkup.setKeyboard(List.of(row1, row2));
        return keyboardMarkup;
    }
}
