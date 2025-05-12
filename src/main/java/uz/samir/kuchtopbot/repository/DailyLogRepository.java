package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.samir.kuchtopbot.model.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
}
