package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SideEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String details;

    @ManyToOne
    private Drug drug;

    public SideEffect(String details, Drug drug) {
        this.details = details;
        this.drug = drug;
    }
}
