package uz.samir.kuchtopbot.service.modelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.samir.kuchtopbot.model.RelapseLog;
import uz.samir.kuchtopbot.repository.RelapseLogRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelapseLogService {

    private final RelapseLogRepository relapseLogRepository;

    public void saveRelapse(Long userId, Long streakId, String note) {
        RelapseLog log = new RelapseLog();
        log.setUserId(userId);
        log.setStreakId(streakId);
        log.setRelapsedAt(LocalDateTime.now());
        log.setNote(note);
        relapseLogRepository.save(log);
    }

    public Optional<RelapseLog> getLastRelapse(Long userId) {
        return relapseLogRepository.findTopByUserIdOrderByRelapsedAtDesc(userId);
    }

    public List<RelapseLog> getAllRelapses(Long userId) {
        return relapseLogRepository.findAllByUserIdOrderByRelapsedAtDesc(userId);
    }
}
