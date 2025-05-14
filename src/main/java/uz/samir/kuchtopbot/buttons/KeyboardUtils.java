package uz.samir.kuchtopbot.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.samir.kuchtopbot.service.bot.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyboardUtils {

    private final MessageService messageService;

    // 🔹 **Til tanlash uchun reply keyboard**
    public static ReplyKeyboardMarkup getLanguageKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("🇺🇿 O‘zbekcha"));
        row1.add(new KeyboardButton("🇷🇺 Русский"));
        row1.add(new KeyboardButton("🇬🇧 English"));

        keyboardRows.add(row1);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getUserMenuKeyboard(long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        return getReplyKeyboardMarkup(messageService, chatId, keyboardMarkup);
    }

    public static ReplyKeyboardMarkup getPreStartMenu(MessageService messageService, long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(messageService.getMessage(chatId, "start_nofap")));
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static ReplyKeyboardMarkup getActiveNofapMenu(MessageService messageService, long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        return getReplyKeyboardMarkup(messageService, chatId, keyboardMarkup);
    }


    private static ReplyKeyboardMarkup getReplyKeyboardMarkup(MessageService messageService, long chatId, ReplyKeyboardMarkup keyboardMarkup) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(messageService.getMessage(chatId, "menu_progress")));
        row1.add(new KeyboardButton(messageService.getMessage(chatId, "menu_motivation")));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(messageService.getMessage(chatId, "menu_reset")));
        row2.add(new KeyboardButton(messageService.getMessage(chatId, "menu_settings")));

        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}