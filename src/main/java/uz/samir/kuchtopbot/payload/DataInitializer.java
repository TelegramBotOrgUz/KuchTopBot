package uz.samir.kuchtopbot.payload;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.model.MotivationMessage;
import uz.samir.kuchtopbot.repository.MotivationMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MotivationMessageRepository motivationMessageRepository;

    @Override
    public void run(String... args) {
        if (motivationMessageRepository.count() == 0) {
            List<MotivationMessage> messages = new ArrayList<>();
            // 50 ta o'zbekcha motivatsion xabar
            messages.add(new MotivationMessage(null, "uz", "Bugun yangi imkoniyat!"));
            messages.add(new MotivationMessage(null, "uz", "Sabr qilgan kuchli bo‘ladi!"));
            messages.add(new MotivationMessage(null, "uz", "Har kun o‘zgarish uchun bir qadam."));
            messages.add(new MotivationMessage(null, "uz", "O‘z ustingda ishlash hech qachon to‘xtamasin."));
            messages.add(new MotivationMessage(null, "uz", "Bugun boshlagan ish ertaga muvaffaqiyat bo‘ladi."));
            messages.add(new MotivationMessage(null, "uz", "Harakat qilmasang natija bo‘lmaydi."));
            messages.add(new MotivationMessage(null, "uz", "Orzularingiz uchun kurashing."));
            messages.add(new MotivationMessage(null, "uz", "Qiyinchiliklar sizni kuchaytiradi."));
            messages.add(new MotivationMessage(null, "uz", "Yengilmaslikka qaror qiling."));
            messages.add(new MotivationMessage(null, "uz", "Har kuni yangi imkoniyat."));
            messages.add(new MotivationMessage(null, "uz", "Harakatlar mevalarini beradi."));
            messages.add(new MotivationMessage(null, "uz", "Dunyoni o‘zgartirish uchun o‘zingizni o‘zgartiring."));
            messages.add(new MotivationMessage(null, "uz", "Sizning kuchingiz ichingizda."));
            messages.add(new MotivationMessage(null, "uz", "Bugun o‘z yo‘lingizni tanlang."));
            messages.add(new MotivationMessage(null, "uz", "O‘zingizga ishoning."));
            messages.add(new MotivationMessage(null, "uz", "Kuchli bo‘lish uchun intiling."));
            messages.add(new MotivationMessage(null, "uz", "Har kun yangi imkoniyatlar bilan to‘ladi."));
            messages.add(new MotivationMessage(null, "uz", "Yutuq sizni kutmoqda."));
            messages.add(new MotivationMessage(null, "uz", "Hech qachon taslim bo‘lmang."));
            messages.add(new MotivationMessage(null, "uz", "Bugun harakat qil."));
            messages.add(new MotivationMessage(null, "uz", "Harakatlaringiz natijasini ko‘rasiz."));
            messages.add(new MotivationMessage(null, "uz", "Dunyo sizga qaraydi."));
            messages.add(new MotivationMessage(null, "uz", "Kuchni ichingizdan toping."));
            messages.add(new MotivationMessage(null, "uz", "Orzularingiz uchun harakat qiling."));
            messages.add(new MotivationMessage(null, "uz", "Sabr va matonat bilan oldinga boring."));
            messages.add(new MotivationMessage(null, "uz", "Bugun yangi imkoniyat boshlanishidir."));
            messages.add(new MotivationMessage(null, "uz", "Har kuni yaxshilaning."));
            messages.add(new MotivationMessage(null, "uz", "Kuchli bo‘lish uchun o‘z ustingizda ishlang."));
            messages.add(new MotivationMessage(null, "uz", "Harakatlaringiz yutuqqa olib keladi."));
            messages.add(new MotivationMessage(null, "uz", "Siz qila olasiz!"));
            messages.add(new MotivationMessage(null, "uz", "Bugun sizning kuningiz."));
            messages.add(new MotivationMessage(null, "uz", "Orzularingizni amalga oshiring."));
            messages.add(new MotivationMessage(null, "uz", "Harakat bilan natija keladi."));
            messages.add(new MotivationMessage(null, "uz", "Yangi kun yangi imkoniyat."));
            messages.add(new MotivationMessage(null, "uz", "Kuchingizni ichingizdan toping."));
            messages.add(new MotivationMessage(null, "uz", "Harakatlaringiz natijasi ko‘rinadi."));
            messages.add(new MotivationMessage(null, "uz", "Sizni yutuq kutmoqda."));
            messages.add(new MotivationMessage(null, "uz", "Hech qachon taslim bo‘lmang."));
            messages.add(new MotivationMessage(null, "uz", "Sabr qiling, muvaffaqiyat yaqin."));
            messages.add(new MotivationMessage(null, "uz", "Bugun kuchingizni sinab ko‘ring."));
            messages.add(new MotivationMessage(null, "uz", "Yutuq sizga qaraydi."));
            messages.add(new MotivationMessage(null, "uz", "Orzularingiz uchun kurashing."));
            messages.add(new MotivationMessage(null, "uz", "Bugun harakat qil."));
            messages.add(new MotivationMessage(null, "uz", "Harakatlaringiz sizni kuchli qiladi."));
            messages.add(new MotivationMessage(null, "uz", "Siz muvaffaqiyatga erishasiz."));
            messages.add(new MotivationMessage(null, "uz", "O‘z ustingizda ishlashni hech qachon to‘xtatmang."));
            messages.add(new MotivationMessage(null, "uz", "Har bir kun yangi imkoniyat."));
            messages.add(new MotivationMessage(null, "uz", "Kuchli bo‘lish oson emas, lekin arziydi."));
            messages.add(new MotivationMessage(null, "uz", "Muvaffaqiyatli bo‘lish uchun harakat qiling."));

            // 50 ta ruscha motivatsion xabar
            messages.add(new MotivationMessage(null, "ru", "Сегодня новый шанс!"));
            messages.add(new MotivationMessage(null, "ru", "Терпение – ключ к успеху."));
            messages.add(new MotivationMessage(null, "ru", "Каждый день – новый шаг."));
            messages.add(new MotivationMessage(null, "ru", "Работайте над собой постоянно."));
            messages.add(new MotivationMessage(null, "ru", "Начинайте сегодня, добейтесь завтра."));
            messages.add(new MotivationMessage(null, "ru", "Без усилий нет результатов."));
            messages.add(new MotivationMessage(null, "ru", "Боритесь за свои мечты."));
            messages.add(new MotivationMessage(null, "ru", "Трудности делают сильнее."));
            messages.add(new MotivationMessage(null, "ru", "Решите не сдаваться."));
            messages.add(new MotivationMessage(null, "ru", "Каждый день – возможность."));
            messages.add(new MotivationMessage(null, "ru", "Ваши усилия окупятся."));
            messages.add(new MotivationMessage(null, "ru", "Измените себя, чтобы изменить мир."));
            messages.add(new MotivationMessage(null, "ru", "Сила внутри вас."));
            messages.add(new MotivationMessage(null, "ru", "Выберите свой путь сегодня."));
            messages.add(new MotivationMessage(null, "ru", "Верьте в себя."));
            messages.add(new MotivationMessage(null, "ru", "Стремитесь быть сильнее."));
            messages.add(new MotivationMessage(null, "ru", "Каждый день – новые возможности."));
            messages.add(new MotivationMessage(null, "ru", "Успех ждет вас."));
            messages.add(new MotivationMessage(null, "ru", "Никогда не сдавайтесь."));
            messages.add(new MotivationMessage(null, "ru", "Действуйте сегодня."));
            messages.add(new MotivationMessage(null, "ru", "Ваши действия принесут плоды."));
            messages.add(new MotivationMessage(null, "ru", "Мир смотрит на вас."));
            messages.add(new MotivationMessage(null, "ru", "Найдите силу внутри."));
            messages.add(new MotivationMessage(null, "ru", "Боритесь за свои мечты."));
            messages.add(new MotivationMessage(null, "ru", "Терпение и настойчивость."));
            messages.add(new MotivationMessage(null, "ru", "Сегодня новый старт."));
            messages.add(new MotivationMessage(null, "ru", "Улучшайте себя ежедневно."));
            messages.add(new MotivationMessage(null, "ru", "Работайте над собой каждый день."));
            messages.add(new MotivationMessage(null, "ru", "Ваши усилия приведут к успеху."));
            messages.add(new MotivationMessage(null, "ru", "Вы можете это сделать!"));
            messages.add(new MotivationMessage(null, "ru", "Сегодня ваш день."));
            messages.add(new MotivationMessage(null, "ru", "Осуществляйте свои мечты."));
            messages.add(new MotivationMessage(null, "ru", "Каждое действие важно."));
            messages.add(new MotivationMessage(null, "ru", "Новый день – новые возможности."));
            messages.add(new MotivationMessage(null, "ru", "Найдите силу внутри."));
            messages.add(new MotivationMessage(null, "ru", "Ваш успех близок."));
            messages.add(new MotivationMessage(null, "ru", "Никогда не сдавайтесь."));
            messages.add(new MotivationMessage(null, "ru", "Терпение – залог успеха."));
            messages.add(new MotivationMessage(null, "ru", "Сегодня проверьте свои силы."));
            messages.add(new MotivationMessage(null, "ru", "Успех смотрит на вас."));
            messages.add(new MotivationMessage(null, "ru", "Боритесь за свои цели."));
            messages.add(new MotivationMessage(null, "ru", "Действуйте сегодня."));
            messages.add(new MotivationMessage(null, "ru", "Ваши усилия делают вас сильнее."));
            messages.add(new MotivationMessage(null, "ru", "Вы добьетесь успеха."));
            messages.add(new MotivationMessage(null, "ru", "Никогда не переставайте работать над собой."));
            messages.add(new MotivationMessage(null, "ru", "Каждый день – новый шанс."));
            messages.add(new MotivationMessage(null, "ru", "Сильным быть нелегко, но это того стоит."));
            messages.add(new MotivationMessage(null, "ru", "Действуйте ради успеха."));

            // 50 English motivational messages
            messages.add(new MotivationMessage(null, "en", "Today is a new opportunity!"));
            messages.add(new MotivationMessage(null, "en", "Patience makes you stronger!"));
            messages.add(new MotivationMessage(null, "en", "Every day is a step towards change."));
            messages.add(new MotivationMessage(null, "en", "Never stop improving yourself."));
            messages.add(new MotivationMessage(null, "en", "What you start today becomes success tomorrow."));
            messages.add(new MotivationMessage(null, "en", "No result without effort."));
            messages.add(new MotivationMessage(null, "en", "Fight for your dreams."));
            messages.add(new MotivationMessage(null, "en", "Challenges make you stronger."));
            messages.add(new MotivationMessage(null, "en", "Decide to be undefeated."));
            messages.add(new MotivationMessage(null, "en", "Every day brings new opportunities."));
            messages.add(new MotivationMessage(null, "en", "Your efforts will pay off."));
            messages.add(new MotivationMessage(null, "en", "Change yourself to change the world."));
            messages.add(new MotivationMessage(null, "en", "The strength is within you."));
            messages.add(new MotivationMessage(null, "en", "Choose your path today."));
            messages.add(new MotivationMessage(null, "en", "Believe in yourself."));
            messages.add(new MotivationMessage(null, "en", "Strive to be strong."));
            messages.add(new MotivationMessage(null, "en", "Each day is filled with new possibilities."));
            messages.add(new MotivationMessage(null, "en", "Success is waiting for you."));
            messages.add(new MotivationMessage(null, "en", "Never give up."));
            messages.add(new MotivationMessage(null, "en", "Take action today."));
            messages.add(new MotivationMessage(null, "en", "Your actions will bring results."));
            messages.add(new MotivationMessage(null, "en", "The world is watching you."));
            messages.add(new MotivationMessage(null, "en", "Find the strength within."));
            messages.add(new MotivationMessage(null, "en", "Act for your dreams."));
            messages.add(new MotivationMessage(null, "en", "Patience and perseverance lead forward."));
            messages.add(new MotivationMessage(null, "en", "Today is a new beginning."));
            messages.add(new MotivationMessage(null, "en", "Improve yourself every day."));
            messages.add(new MotivationMessage(null, "en", "Work on yourself to become stronger."));
            messages.add(new MotivationMessage(null, "en", "Your efforts lead to success."));
            messages.add(new MotivationMessage(null, "en", "You can do it!"));
            messages.add(new MotivationMessage(null, "en", "Today is your day."));
            messages.add(new MotivationMessage(null, "en", "Make your dreams come true."));
            messages.add(new MotivationMessage(null, "en", "Action brings results."));

            motivationMessageRepository.saveAll(messages);
        }
    }
}