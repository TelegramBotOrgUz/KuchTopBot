package uz.samir.kuchtopbot.service.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.samir.kuchtopbot.telegram.WillUpBot;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final UserRepository userRepo;
    private final WillUpBot willUpBot;


    @Scheduled(cron = "0 0 20 * * *") // Har kuni 20:00 da
    public void sendReminders() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.isActive()) {
                SendMessage message = new SendMessage(user.getChatId().toString(), "KuchTopBot eslatadi: Bugungi NoFap holatingizni /log buyrug'i bilan baholang. Siz kuch topa olasiz! 💪");
                try {
                    willUpBot.execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
