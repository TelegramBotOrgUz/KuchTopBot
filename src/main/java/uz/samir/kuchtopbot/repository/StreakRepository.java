package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.samir.kuchtopbot.model.Streak;

import java.util.List;
import java.util.Optional;

public interface StreakRepository extends JpaRepository<Streak, Long> {
    List<Streak> findAllByUserIdOrderByStartedAtDesc(Long userId);

    Optional<Streak> findByUserIdAndEndedAtIsNull(Long userId); // hozirgi faol streak

    @Query("SELECT MAX(s.durationDays) FROM Streak s WHERE s.userId = :userId")
    Optional<Integer> findBestStreak(@Param("userId") Long userId);

    @Query("SELECT s FROM Streak s WHERE s.userId = :userId AND s.relapsed = true ORDER BY s.endedAt DESC LIMIT 1")
    Optional<Streak> findLastRelapse(@Param("userId") Long userId);

    List<Streak> findAllByUserId(Long userId);
}
