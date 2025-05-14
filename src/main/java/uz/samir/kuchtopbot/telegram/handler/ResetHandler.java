package uz.samir.kuchtopbot.telegram.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.buttons.InlineKeyboardUtil;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.UserService;
import uz.samir.kuchtopbot.service.modelService.RelapseLogService;

@Component
@RequiredArgsConstructor
public class ResetHandler {

    private final TelegramBotMessageController messageController;
    private final UserService userService;
    private final MessageService messageService;
    private final RelapseLogService relapseLogService;
    private final InlineKeyboardUtil inlineKeyboardUtil;

    public void handleReset(Long chatId) {
        // Foydalanuvchining NoFap progressini reset qilishdan oldin relapsni loglash
        var user = userService.getUser(chatId);
        var optionalStreak = userService.getActiveStreak(user.getId());

        optionalStreak.ifPresent(streak -> {
            // Relaps sababini so‘rash
            String relapseReason = "askForRelapseReason(chatId)"; // Yangi metod (foydalanuvchidan sababni so‘rish)

            // Agar sabab bo‘lmasa, default yozish
            if (relapseReason == null || relapseReason.isEmpty()) {
                relapseReason = "Siz 'Reset qilish' tugmasini bosdingiz.";
            }

            // Relaps logini saqlash
            relapseLogService.saveRelapse(user.getId(), streak.getId(), relapseReason);
        });

        // Streakni reset qilish
        userService.resetStreak(chatId);

        // Xabar yuborish
        String resetMessage = messageService.getMessage(chatId, "reset_message");
        messageController.sendMessage(chatId, resetMessage);
    }

    private void askForRelapseReason(Long chatId) {
        String message = "Relaps sababini kiriting yoki quyidagi tugmalardan birini tanlang:";
        messageController.sendMessage(chatId, message, inlineKeyboardUtil.askForRelapseReason());
    }
}
