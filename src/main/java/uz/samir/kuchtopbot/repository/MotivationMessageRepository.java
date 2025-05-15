package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.samir.kuchtopbot.model.MotivationMessage;

import java.util.List;

public interface MotivationMessageRepository extends JpaRepository<MotivationMessage, Long> {

    // Berilgan til bo‘yicha barcha motivatsion xabarlarni qaytaradi
    List<MotivationMessage> findAllByLanguage(String language);
}