package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.service.modelService.MotivationService;

@Component
@RequiredArgsConstructor
public class AdminMotivationHandler {

    private final MotivationService motivationService;
    private final TelegramBotMessageController telegramBotMessageController;

    public void handleAddMotivation(Long chatId, String text) {
        // Kutamiz formatni: /addmotivation:uz:Matn
        if (!text.startsWith("/addmotivation:")) return;

        try {
            String[] parts = text.split(":", 3);
            String lang = parts[1];
            String content = parts[2];

            motivationService.saveMotivation(lang, content);
            telegramBotMessageController.sendMessage(chatId, "✅ Motivatsion xabar saqlandi.");
        } catch (Exception e) {
            telegramBotMessageController.sendMessage(chatId, "❌ Xatolik! Format: /addmotivation:uz:Matn");
        }
    }
}
