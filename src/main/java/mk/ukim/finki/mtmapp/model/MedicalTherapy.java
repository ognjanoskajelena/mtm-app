package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@Entity
public class MedicalTherapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1024)
    private String details;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "medicalTherapy", orphanRemoval = true)
    private Collection<Drug> drugs;

    public MedicalTherapy() {
        this.drugs = new ArrayList<>();
    }

    public MedicalTherapy(String name, String details, User user) {
        this.name = name;
        this.details = details;
        this.user = user;
        this.drugs = new ArrayList<>();
    }
}
