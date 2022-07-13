package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.User;

import java.util.Optional;

public interface MedicalTherapyService {
    Optional<MedicalTherapy> findById(Long id);

    MedicalTherapy save(String name, String details, User user);

    MedicalTherapy update(Long id, String name, String details);

    void deleteById(Long id);

    void clearTherapy(Long id);

    MedicalTherapy addDrugToTherapy(Drug drug, Long therapyId);

    MedicalTherapy removeDrugFromTherapy(Drug drug, Long therapyId);
}
