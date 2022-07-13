package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer times;

    private Integer pills;

    @OneToOne
    private Drug drug;

    public Dose(Integer times, Integer pills) {
        this.times = times;
        this.pills = pills;
    }

    public Dose(Integer times, Integer pills, Drug drug) {
        this.times = times;
        this.pills = pills;
        this.drug = drug;
    }
}
