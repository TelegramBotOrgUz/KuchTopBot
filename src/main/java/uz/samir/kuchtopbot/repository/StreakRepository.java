package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.samir.kuchtopbot.model.Streak;

import java.util.List;
import java.util.Optional;

public interface StreakRepository extends JpaRepository<Streak, Long> {
    List<Streak> findAllByUserIdOrderByStartedAtDesc(Long userId);
    Optional<Streak> findByUserIdAndEndedAtIsNull(Long userId); // hozirgi faol streak
}
