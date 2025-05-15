package uz.samir.kuchtopbot.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.samir.kuchtopbot.service.bot.MessageService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InlineKeyboardUtil {

    private final MessageService messageService;

    public InlineKeyboardMarkup askForRelapseReason(long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        String relapseReasonNone = messageService.getMessage(chatId, "relapse_reason_none");
        String relapseReasonWrite = messageService.getMessage(chatId, "relapse_reason_write");
        String cancelText = messageService.getMessage(chatId, "cancel");

        InlineKeyboardButton button1 = new InlineKeyboardButton(relapseReasonNone);
        InlineKeyboardButton button2 = new InlineKeyboardButton(relapseReasonWrite);
        InlineKeyboardButton button3 = new InlineKeyboardButton(cancelText);

        button1.setCallbackData("no_reason"); // Sababsiz tugma
        button2.setCallbackData("write_reason"); // Sabab yozish tugmasi
        button3.setCallbackData("cancel");

        List<InlineKeyboardButton> row1 = List.of(button1);
        List<InlineKeyboardButton> row2 = List.of(button2);
        List<InlineKeyboardButton> row3 = List.of(button3);
        keyboardMarkup.setKeyboard(List.of(row1, row2, row3));
        return keyboardMarkup;
    }
}
