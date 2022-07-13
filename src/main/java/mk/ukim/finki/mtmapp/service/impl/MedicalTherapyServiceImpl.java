package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.MedicalTherapyRepository;
import mk.ukim.finki.mtmapp.service.MedicalTherapyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MedicalTherapyServiceImpl implements MedicalTherapyService {

    private final MedicalTherapyRepository medicalTherapyRepository;

    @Override
    public Optional<MedicalTherapy> findById(Long id) {
        log.info("Getting medical therapy by id: {}", id);
        return this.medicalTherapyRepository.findById(id);
    }

    @Override
    public MedicalTherapy save(String name, String details, User user) {
        log.info("Saving medical therapy with name: {}, for user with username: {}", name, user.getUsername());
        return this.medicalTherapyRepository.save(new MedicalTherapy(name, details, user));
    }

    @Override
    public MedicalTherapy update(Long id, String name, String details) {
        Optional<MedicalTherapy> optionalMedicalTherapy = this.findById(id);
        if (optionalMedicalTherapy.isPresent()) {
            MedicalTherapy medicalTherapy = optionalMedicalTherapy.get();
            medicalTherapy.setName(name);
            medicalTherapy.setDetails(details);
            log.info("Updating medical therapy by id: {}", id);
            return this.medicalTherapyRepository.save(medicalTherapy);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", id));
    }

    @Override
    public void deleteById(Long id) {
        this.medicalTherapyRepository.deleteById(id);
        log.info("Deleted medical therapy by id: {}", id);
    }

    @Override
    public void clearTherapy(Long id) {
        Optional<MedicalTherapy> optionalMedicalTherapy = this.findById(id);
        if (optionalMedicalTherapy.isPresent()) {
            MedicalTherapy medicalTherapy = optionalMedicalTherapy.get();
            medicalTherapy.getDrugs().clear();
            log.info("Clearing all drugs from medical therapy by id: {}", id);
            this.medicalTherapyRepository.save(medicalTherapy);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", id));
    }

    @Override
    public MedicalTherapy addDrugToTherapy(Drug drug, Long therapyId) {
        Optional<MedicalTherapy> optionalMedicalTherapy = this.findById(therapyId);
        if (optionalMedicalTherapy.isPresent()) {
            MedicalTherapy medicalTherapy = optionalMedicalTherapy.get();
            medicalTherapy.getDrugs().add(drug);
            log.info("Adding drug with id: {}, to medical therapy with id: {}", drug.getId(), therapyId);
            this.medicalTherapyRepository.save(medicalTherapy);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", therapyId));
    }

    @Override
    public MedicalTherapy removeDrugFromTherapy(Drug drug, Long therapyId) {
        Optional<MedicalTherapy> optionalMedicalTherapy = this.findById(therapyId);
        if (optionalMedicalTherapy.isPresent()) {
            MedicalTherapy medicalTherapy = optionalMedicalTherapy.get();
            medicalTherapy.getDrugs().remove(drug);
            log.info("Removing drug with id: {}, from medical therapy with id: {}", drug.getId(), therapyId);
            this.medicalTherapyRepository.save(medicalTherapy);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", therapyId));
    }
}
