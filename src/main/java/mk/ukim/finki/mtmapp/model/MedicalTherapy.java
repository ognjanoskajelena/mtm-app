package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.mtmapp.model.enums.MedicalTherapyStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Drug> drugsGotten;

    private MedicalTherapyStatus medicalTherapyStatus;

    public MedicalTherapy() {
        this.medicalTherapyStatus = MedicalTherapyStatus.UNCOMPLETED;
        this.drugs = new ArrayList<>();
        this.drugsGotten = new HashSet<>();
    }

    public MedicalTherapy(String name, String details, User user) {
        this.name = name;
        this.details = details;
        this.user = user;
        this.drugs = new ArrayList<>();
        this.drugsGotten = new HashSet<>();
        this.medicalTherapyStatus = MedicalTherapyStatus.UNCOMPLETED;
    }

    public void addDrugsGotten(Drug drug) {
        this.drugsGotten.add(drug);
    }

    public void completeTherapy() {
        this.medicalTherapyStatus = MedicalTherapyStatus.COMPLETED;
    }

    public void clearTherapyStatus() {
        this.getDrugsGotten().clear();
        this.medicalTherapyStatus = MedicalTherapyStatus.UNCOMPLETED;
    }
}
