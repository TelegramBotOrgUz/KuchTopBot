package uz.samir.kuchtopbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.samir.kuchtopbot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(Long chatId);
}
