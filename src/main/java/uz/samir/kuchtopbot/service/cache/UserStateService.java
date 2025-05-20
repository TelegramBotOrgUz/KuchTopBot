package uz.samir.kuchtopbot.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.template.BotState;

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
        redisTemplate.opsForValue().set(KEY_PREFIX + chatId, messageId.toString(), EXPIRE_DAYS, TimeUnit.DAYS);
    }

    public Optional<Integer> getLastMessageId(Long chatId) {
        String key = KEY_PREFIX + chatId;
        String val = redisTemplate.opsForValue().get(key);
        if (val != null) {
            redisTemplate.expire(key, EXPIRE_DAYS, TimeUnit.DAYS);
            return Optional.of(Integer.parseInt(val));
        }
        return Optional.empty();
    }

    public void saveState(Long chatId, String state) {
        String key = STATE_PREFIX + chatId;
        redisTemplate.opsForValue().set(key, state, EXPIRE_DAYS, TimeUnit.DAYS);
    }


    public String getState(Long chatId) {
        String key = STATE_PREFIX + chatId;
        String state = redisTemplate.opsForValue().get(key);
        if (state != null) {
            redisTemplate.expire(key, EXPIRE_DAYS, TimeUnit.DAYS);
            return state;
        }
        return BotState.STARTED.name();
    }

    public void saveLanguage(Long chatId, String language) {
        redisTemplate.opsForValue().set(LANG_PREFIX + chatId, language, EXPIRE_DAYS, TimeUnit.DAYS);
    }

    public String getLanguage(Long chatId) {
        String language = redisTemplate.opsForValue().get(LANG_PREFIX + chatId);
        if (language != null) {
            redisTemplate.expire(LANG_PREFIX + chatId, EXPIRE_DAYS, TimeUnit.DAYS);
            return language;
        }
        return "uz";
    }
}