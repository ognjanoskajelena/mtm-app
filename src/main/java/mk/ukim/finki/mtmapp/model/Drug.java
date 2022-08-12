package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mtmapp.model.enums.Use;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer dose;

    @Enumerated(EnumType.STRING)
    private Use use;

    private Integer stockpile;

    @OneToMany(mappedBy = "drug", orphanRemoval = true)
    private Collection<SideEffect> sideEffects = new ArrayList<>();

    @ManyToOne
    private MedicalTherapy medicalTherapy;

    public Drug(String name, Integer dose, Use use, Integer stockpile, MedicalTherapy medicalTherapy) {
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

    public void decreaseStockpile() {
        if ((this.stockpile - this.dose) > 0) {
            this.stockpile = this.stockpile - this.dose;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drug)) return false;
        Drug drug = (Drug) o;
        return id.equals(drug.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
