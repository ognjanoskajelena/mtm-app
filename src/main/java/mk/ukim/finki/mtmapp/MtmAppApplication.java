package mk.ukim.finki.mtmapp;

import mk.ukim.finki.mtmapp.model.Dose;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.model.enums.Use;
import mk.ukim.finki.mtmapp.repository.DoseRepository;
import mk.ukim.finki.mtmapp.repository.DrugRepository;
import mk.ukim.finki.mtmapp.repository.MedicalTherapyRepository;
import mk.ukim.finki.mtmapp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MtmAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(MtmAppApplication.class, args);

        // User
        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);
        User test = new User("Test", "Test", "test", "tt",
                "test@test.com", 45);
        userRepository.save(test);

        // Dose
        DoseRepository doseRepository = configurableApplicationContext.getBean(DoseRepository.class);
        Dose dose = new Dose(2, 1);
        doseRepository.save(dose);

        //Medical therapy
        MedicalTherapyRepository medicalTherapyRepository =
                configurableApplicationContext.getBean(MedicalTherapyRepository.class);
        MedicalTherapy medicalTherapy = new MedicalTherapy("Terapija 1", "Detali za terapija 1", test);
        medicalTherapyRepository.save(medicalTherapy);

        // Drugs
        DrugRepository drugRepository = configurableApplicationContext.getBean(DrugRepository.class);
        Drug drug = new Drug("Lek 1", dose, Use.BEFORE_MEAL, 30, medicalTherapy);
        drugRepository.save(drug);
        drug = new Drug("Lek 2", dose, Use.AFTER_MEAL, 45, medicalTherapy);
        drugRepository.save(drug);

        medicalTherapy.getDrugs().add(drug);
        medicalTherapyRepository.save(medicalTherapy);

        test.setMedicalTherapy(medicalTherapy);
        userRepository.save(test);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
