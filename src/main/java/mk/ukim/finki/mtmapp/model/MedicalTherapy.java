package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
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
    private Collection<Drug> drugs = new ArrayList<>();

    public MedicalTherapy(String name, String details, User user) {
        this.name = name;
        this.details = details;
        this.user = user;
    }
}
