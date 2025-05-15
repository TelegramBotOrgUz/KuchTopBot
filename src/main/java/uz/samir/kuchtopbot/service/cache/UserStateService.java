package uz.samir.kuchtopbot.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserStateService {
    private final StringRedisTemplate redisTemplate;
    private static final String STATE_PREFIX = "user:state:";
    private static final String LANG_PREFIX = "user:lang:";
    private static final String ID_PREFIX = "user:id:";
    private static final String TEMP_DATA_PREFIX = "user:temp:";
    private static final long EXPIRE_DAYS = 7; // Ma'lumotlarni 7 kun saqlaymiz

    // ✅ Foydalanuvchi state ni saqlash
    public void saveState(Long chatId, String state) {
        String key = STATE_PREFIX + chatId;
        redisTemplate.opsForValue().set(key, state, Duration.ofDays(EXPIRE_DAYS));
    }

    // ✅ Foydalanuvchi state ni olish
    public String getState(Long chatId) {
        return redisTemplate.opsForValue().get(STATE_PREFIX + chatId);
    }

    // ✅ Foydalanuvchi tilini saqlash
    public void saveLanguage(Long chatId, String language) {
        redisTemplate.opsForValue().set(LANG_PREFIX + chatId, language, EXPIRE_DAYS, TimeUnit.DAYS);
    }

    // ✅ Foydalanuvchi tilini olish (default "uz")
    public String getLanguage(Long chatId) {
        String language = redisTemplate.opsForValue().get(LANG_PREFIX + chatId);
        return (language != null) ? language : "uz";
    }

    public void saveLastMessage(Long chatId, Integer messageId) {
        redisTemplate.opsForValue().set(LANG_PREFIX + chatId, messageId.toString(), EXPIRE_DAYS, TimeUnit.DAYS);
    }

    public String getLastMessage(Long chatId) {
        return redisTemplate.opsForValue().get(LANG_PREFIX + chatId);
    }

}