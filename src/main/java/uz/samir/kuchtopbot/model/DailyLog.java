package uz.samir.kuchtopbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyLog {
    @Id
    @GeneratedValue
    private Long id;
    private Long chatId;
    private LocalDate date;
    private boolean cleanDay; // true = relapse yo'q
    private String mood;
    private String notes;
}
