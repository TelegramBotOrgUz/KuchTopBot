package uz.samir.kuchtopbot.service;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String get(String key, Locale locale, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }

    public String get(String key, String lang, Object... args) {
        Locale locale = resolveLocale(lang);
        return get(key, locale, args);
    }

    public Locale resolveLocale(String lang) {
        if (lang == null) return Locale.ENGLISH;
        return switch (lang.toUpperCase()) {
            case "UZ" -> new Locale("uz");
            case "RU" -> new Locale("ru");
            default -> Locale.ENGLISH;
        };
    }
}