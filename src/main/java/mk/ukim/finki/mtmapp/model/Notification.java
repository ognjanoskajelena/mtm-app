package mk.ukim.finki.mtmapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User owner;

    private String content;

    private LocalDateTime sentAt;

    private boolean seen;

    public Notification(User owner, String content) {
        this.owner = owner;
        this.content = content;
        this.sentAt = LocalDateTime.now();
        this.seen = false;
    }
}
