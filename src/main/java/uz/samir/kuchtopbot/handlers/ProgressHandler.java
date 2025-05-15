package uz.samir.kuchtopbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.samir.kuchtopbot.controller.TelegramBotMessageController;
import uz.samir.kuchtopbot.model.RelapseLog;
import uz.samir.kuchtopbot.model.Streak;
import uz.samir.kuchtopbot.model.User;
import uz.samir.kuchtopbot.payload.UserStatsDto;
import uz.samir.kuchtopbot.service.bot.MessageService;
import uz.samir.kuchtopbot.service.modelService.RelapseLogService;
import uz.samir.kuchtopbot.service.modelService.StreakService;
import uz.samir.kuchtopbot.service.modelService.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgressHandler {

    private final StreakService streakService;
    private final MessageService messageService;
    private final TelegramBotMessageController messageController;
    private final UserService userService;
    private final RelapseLogService relapseLogService;

    public void handleStats(Long chatId) {
        UserStatsDto stats = streakService.getStats(chatId);

        String formattedStart = stats.getNofapStartedAt() != null
                ? stats.getNofapStartedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                : "-";

        String formattedRelapse = stats.getLastRelapseAt() != null
                ? stats.getLastRelapseAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                : "-";

        String msg = String.format(
                messageService.getMessage(chatId, "stats_message"),
                stats.getCurrentStreakDays(),
                formattedStart,
                stats.getBestStreakDays(),
                formattedRelapse
        );

        messageController.sendMessage(chatId, msg);
    }

    public void showRelapseStats(Long chatId) {
        User user = userService.getUser(chatId);
        Long userId = user.getId();

        List<RelapseLog> relapseLogs = relapseLogService.getAllRelapses(userId);
        List<Streak> userStreaks = streakService.getAllByUser(userId);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder relapseHistoryBuilder = new StringBuilder();

        int totalNoFapDays = userStreaks.stream().mapToInt(Streak::getDurationDays).sum();
        int totalFapDays = calculateFapDays(userStreaks);

        for (RelapseLog log : relapseLogs) {
            streakService.getById(log.getStreakId()).ifPresent(streak -> {
                String started = streak.getStartedAt().format(dateFormat);
                String ended = streak.getEndedAt() != null ? streak.getEndedAt().format(dateFormat) : "-";
                int days = streak.getDurationDays();

                relapseHistoryBuilder
                    .append(String.format(messageService.getMessage(chatId, "relapse_entry_date"), log.getRelapsedAt().format(dateFormat))).append("\n")
                    .append(String.format(messageService.getMessage(chatId, "relapse_entry_streak"), started, ended, days)).append("\n")
                    .append(String.format(messageService.getMessage(chatId, "relapse_entry_note"), log.getNote())).append("\n\n");
            });
        }

        int totalRelapses = relapseLogs.size();
        int relapsesLast30Days = (int) relapseLogs.stream()
            .filter(r -> r.getRelapsedAt().isAfter(LocalDateTime.now().minusDays(30)))
            .count();

        String relapseHistory = relapseHistoryBuilder.toString();

        String finalMessage = String.format(
            messageService.getMessage(chatId, "relapse_stats_summary"),
            relapseHistory,
            totalRelapses,
            relapsesLast30Days,
            totalNoFapDays,
            totalFapDays
        );

        messageController.sendMessage(chatId, finalMessage);
    }

    // Ushbu metod foydalanuvchining streaklari orasidagi "porno bilan kunlar" sonini hisoblaydi
    private int calculateFapDays(List<Streak> streaks) {
        int fapDays = 0;

        // Har bir ketma-ket streaklar orasidagi kunlar farqini hisoblash
        for (int i = 1; i < streaks.size(); i++) {
            Streak prev = streaks.get(i - 1); // oldingi streak
            Streak curr = streaks.get(i);     // hozirgi streak

            // Agar oldingi streak tugagan va yangi streak boshlangani aniqlangan bo‘lsa
            if (prev.getEndedAt() != null && curr.getStartedAt() != null) {
                long gap = Duration.between(prev.getEndedAt(), curr.getStartedAt()).toDays();

                // Agar ikki streak orasida kun farqi bo‘lsa, uni fap kunlariga qo‘shamiz
                if (gap > 0) fapDays += gap;
            }
        }

        // Oxirgi streakdan bugungacha bo‘lgan davrda yana fap kunlari bo‘lishi mumkin
        if (!streaks.isEmpty()) {
            Streak last = streaks.get(streaks.size() - 1);

            // Agar oxirgi streak tugagan bo‘lsa (ya'ni relaps bo‘lgan)
            if (last.getEndedAt() != null) {
                long gap = Duration.between(last.getEndedAt(), LocalDateTime.now()).toDays();

                // Tugagandan hozirgacha oraliq bor bo‘lsa, fap kunlariga qo‘shamiz
                if (gap > 0) fapDays += gap;
            }
        }

        return fapDays; // Jami fap (porno bilan) kunlar
    }
}