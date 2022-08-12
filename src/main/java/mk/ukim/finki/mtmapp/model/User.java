package mk.ukim.finki.mtmapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mtmapp.model.enums.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private MedicalTherapy medicalTherapy;

    public User(String name, String surname, String username, String password, String email, Integer age) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = Role.ROLE_USER;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }
}
