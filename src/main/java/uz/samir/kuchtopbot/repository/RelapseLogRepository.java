package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.samir.kuchtopbot.model.RelapseLog;

import java.util.List;
import java.util.Optional;

public interface RelapseLogRepository extends JpaRepository<RelapseLog, Long> {

    List<RelapseLog> findAllByUserIdOrderByRelapsedAtDesc(Long userId);

    Optional<RelapseLog> findTopByUserIdOrderByRelapsedAtDesc(Long userId);
}
