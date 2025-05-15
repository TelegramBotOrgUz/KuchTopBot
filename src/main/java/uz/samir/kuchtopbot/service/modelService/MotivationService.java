package uz.samir.kuchtopbot.service.modelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.MotivationMessage;
import uz.samir.kuchtopbot.repository.MotivationMessageRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MotivationService {

    private final MotivationMessageRepository motivationMessageRepository;

    public String getRandomMotivation(String lang) {
        List<MotivationMessage> messages = motivationMessageRepository.findAllByLanguage(lang);
        if (messages.isEmpty()) {
            return "Bugun uchun motivatsion xabar yo'q.";
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(messages.size());
        return messages.get(randomIndex).getContent();
    }

    public void saveMotivation(String lang, String content) {
        MotivationMessage motivation = new MotivationMessage();
        motivation.setLanguage(lang);
        motivation.setContent(content);
        motivationMessageRepository.save(motivation);
    }
}
