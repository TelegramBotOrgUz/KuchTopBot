package uz.samir.kuchtopbot.service.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class BotService {
    public String handleMessage(Message message) {
        String text = message.getText();

        // Foydalanuvchidan kelgan so'rovga javob
        switch (text) {
            case "/help":
                return "Sizga qanday yordam bera olishim mumkin? Sizga har doim tayyorman!";
            case "/stats":
                return "Sizning hozirgi statistikangizni ko‘rish uchun foydalanuvchi ma’lumotlarini tekshirish zarur.";
            default:
                return "Uzr, bu buyruqni tushunmadim. Iltimos, to‘g‘ri buyruq kiriting.";
        }
    }
}
