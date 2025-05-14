package uz.samir.kuchtopbot.service.bot;

import org.springframework.stereotype.Service;
import org.springframework.context.MessageSource;
import uz.samir.kuchtopbot.service.cache.UserStateService;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;
    private final UserStateService userStateService;

    public MessageService(MessageSource messageSource, UserStateService userStateService) {
        this.messageSource = messageSource;
        this.userStateService = userStateService;
    }

    public String getMessage(Long chatId, String key) {
        String lang = userStateService.getLanguage(chatId); // Foydalanuvchi tilini Redis'dan olish
        Locale locale = new Locale(lang);
        return messageSource.getMessage(key, null, locale);
    }
}