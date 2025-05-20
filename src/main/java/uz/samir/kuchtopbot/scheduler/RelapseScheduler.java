package uz.samir.kuchtopbot.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.samir.kuchtopbot.buttons.InlineKeyboardUtil;
import uz.samir.kuchtopbot.controller.MessageSender;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.service.modelService.UserService;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelapseScheduler {

    private final UserService userService;
    private final TelegramBotMessageController messageController;
    private final MessageService messageService;
    private final InlineKeyboardUtil inlineKeyboardUtil;
    private final UserStateService userStateService;
    private final MessageSender messageSender;

    // ⏰ Har kuni ertalab soat 08:00 da ishga tushadi
    @Scheduled(cron = "0 0 8 * * *")
    public void askForRelapseCheck() {
        // 1. Hozirda streak holati davom etayotgan foydalanuvchilarni olish
        List<User> activeUsers = userService.getAllWithActiveStreak();

        // 2. Har bir foydalanuvchiga "Relaps bo‘ldimi?" savolini yuborish
        for (User user : activeUsers) {
            Long chatId = user.getChatId();
            String text = messageService.getMessage(chatId, "relapse_daily_check");

            // Eski xabarni o'chirish
            userStateService.getLastMessageId(chatId).ifPresent(oldMsgId ->
                    messageController.deleteMessage(chatId, oldMsgId)
            );
            Message message = messageSender.sendMessageWithResult(
                    chatId,
                    text,
                    inlineKeyboardUtil.getRelapseDailyCheckButtons(chatId) // ✅ Ha | ❌ Yo‘q tugmalar
            );

           try {
                userStateService.saveLastMessageId(chatId, message.getMessageId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
