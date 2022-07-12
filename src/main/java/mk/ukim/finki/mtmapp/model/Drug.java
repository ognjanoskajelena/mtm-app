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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<SideEffect> sideEffects = new ArrayList<>();

    @ManyToOne
    private MedicalTherapy medicalTherapy;
}
