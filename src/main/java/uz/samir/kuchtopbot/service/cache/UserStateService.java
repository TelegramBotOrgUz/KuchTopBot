package uz.samir.kuchtopbot.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserStateService {
    private final StringRedisTemplate redisTemplate;
    private static final String STATE_PREFIX = "user:state:";
    private static final String LANG_PREFIX = "user:lang:";
    private static final long EXPIRE_DAYS = 7; // Ma'lumotlarni 7 kun saqlaymiz
    private static final String KEY_PREFIX = "relapse:lastMessage:";

    public void saveLastMessageId(Long chatId, Integer messageId) {
        redisTemplate.opsForValue().set(KEY_PREFIX + chatId, messageId.toString());
    }

    public Optional<Integer> getLastMessageId(Long chatId) {
        String val = redisTemplate.opsForValue().get(KEY_PREFIX + chatId);
        if (val == null) return Optional.empty();
        return Optional.of(Integer.parseInt(val));
    }

    public void deleteLastMessageId(Long chatId) {
        redisTemplate.delete(KEY_PREFIX + chatId);
    }

    public void saveState(Long chatId, String state) {
        String key = STATE_PREFIX + chatId;
        redisTemplate.opsForValue().set(key, state, Duration.ofDays(EXPIRE_DAYS));
    }

    public String getState(Long chatId) {
        return redisTemplate.opsForValue().get(STATE_PREFIX + chatId);
    }

    public void saveLanguage(Long chatId, String language) {
        redisTemplate.opsForValue().set(LANG_PREFIX + chatId, language, EXPIRE_DAYS, TimeUnit.DAYS);
    }

    public String getLanguage(Long chatId) {
        String language = redisTemplate.opsForValue().get(LANG_PREFIX + chatId);
        return (language != null) ? language : "uz";
    }
}