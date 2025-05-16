package uz.samir.kuchtopbot.handlers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.buttons.InlineKeyboardUtil;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.model.template.BotState;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.cache.UserStateService;
import uz.samir.kuchtopbot.service.modelService.UserService;
import uz.samir.kuchtopbot.service.modelService.RelapseLogService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResetHandler {

    private final TelegramBotMessageController messageController;
    private final UserService userService;
    private final MessageService messageService;
    private final RelapseLogService relapseLogService;
    private final InlineKeyboardUtil inlineKeyboardUtil;
    private final UserStateService userStateService;
    private final MainMenuHandler mainMenuHandler;


    public void askForRelapseReason(Long chatId) {
        String message = messageService.getMessage(chatId,"enter_relapse_note");
        messageController.sendMessage(chatId, message, inlineKeyboardUtil.askForRelapseReason(chatId));
    }


    public void handleResetReason(Long chatId) {
        userStateService.saveState(chatId, BotState.REASON_INPUT.name());
        messageController.sendMessage(chatId,
                messageService.getMessage(chatId, "relapse_reason_request"));
    }

    public void saveReset(Long chatId, String messageText) {
        var user = userService.getUser(chatId);
        Optional<Streak> optionalStreak = userService.getActiveStreak(user.getId());

        optionalStreak.ifPresent(streak ->
                relapseLogService.saveRelapse(user.getId(), streak.getId(), messageText)
        );

        userService.resetStreak(chatId);

        userStateService.saveState(chatId, BotState.START_NOFAP.name());

        String resetMessage = messageService.getMessage(chatId, "reset_message");
        messageController.sendMessage(chatId, resetMessage);

        mainMenuHandler.showMainMenuAll(chatId);
    }
}
