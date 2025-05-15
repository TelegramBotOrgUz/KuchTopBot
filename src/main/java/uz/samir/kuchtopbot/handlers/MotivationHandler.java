package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.service.modelService.MotivationService;
import uz.samir.kuchtopbot.service.modelService.UserService;

@Component
@RequiredArgsConstructor
public class MotivationHandler {

    private final TelegramBotMessageController messageController;
    private final MotivationService motivationService;
    private final UserService userService;
    private final UserStateService userStateService;

    public void handleMotivation(Long chatId) {
        User user = userService.getUser(chatId);
        String motivationMessage = motivationService
                .getRandomMotivation(userStateService.getLanguage(user.getChatId()));
        messageController.sendMessage(chatId, motivationMessage);
    }
}
