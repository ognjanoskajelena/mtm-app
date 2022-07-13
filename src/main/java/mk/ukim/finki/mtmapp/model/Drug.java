package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mtmapp.model.enums.Use;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Dose dose;

    @Enumerated(EnumType.STRING)
    private Use use;

    private Integer stockpile;

    @OneToMany(mappedBy = "drug", orphanRemoval = true)
    private Collection<SideEffect> sideEffects = new ArrayList<>();

    @ManyToOne
    private MedicalTherapy medicalTherapy;

    public Drug(String name, Dose dose, Use use, Integer stockpile, MedicalTherapy medicalTherapy) {
        this.name = name;
        this.dose = dose;
        this.use = use;
        this.stockpile = stockpile;
        this.medicalTherapy = medicalTherapy;
    }

    public void addSideEffect(SideEffect sideEffect) {
        this.sideEffects.add(sideEffect);
    }

    public void removeSideEffect(SideEffect sideEffect) {
        this.sideEffects.remove(sideEffect);
    }
}
