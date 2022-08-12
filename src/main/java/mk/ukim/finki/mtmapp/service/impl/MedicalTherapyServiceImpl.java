package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.MedicalTherapyRepository;
import mk.ukim.finki.mtmapp.service.DrugService;
import mk.ukim.finki.mtmapp.service.MedicalTherapyService;
import mk.ukim.finki.mtmapp.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MedicalTherapyServiceImpl implements MedicalTherapyService {

    private final MedicalTherapyRepository medicalTherapyRepository;
    private final NotificationService notificationService;
    private final DrugService drugService;

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
    public MedicalTherapy update(MedicalTherapy medicalTherapy) {
        return this.medicalTherapyRepository.save(medicalTherapy);
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

    @Override
    public void addDrugGottenToTherapy(Long drugId, MedicalTherapy medicalTherapy) {
        Optional<Drug> optionalDrug = this.drugService.findById(drugId);
        if(optionalDrug.isPresent()) {
            medicalTherapy.addDrugsGotten(optionalDrug.get());
            if (medicalTherapy.getDrugs().size() == medicalTherapy.getDrugsGotten().size()) {
                medicalTherapy.completeTherapy();
                this.createNewNotification(medicalTherapy);
            }
            log.info("Get drug for medical therapy with id: {}", medicalTherapy.getId());
            this.medicalTherapyRepository.save(medicalTherapy);
            return;
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", drugId));
    }

    @Override
    public List<MedicalTherapy> findAll() {
        log.info("Getting all medical therapies");
        return this.medicalTherapyRepository.findAll();
    }

    private void createNewNotification(MedicalTherapy medicalTherapy) {
        User owner = medicalTherapy.getUser();
        StringBuilder content = new StringBuilder();
        content.append(String.format("Dear %s, you have completed your therapy for today.\n", owner.getFullName()));
        content.append("Stocks: ");
        List<Drug> drugs = new ArrayList<>(medicalTherapy.getDrugs());
        for (int i = 0; i < drugs.size(); i++) {
            if (i == drugs.size() - 1) {
                content.append(String.format("%s (%d).", drugs.get(i).getName(), drugs.get(i).getStockpile()));
            } else {
                content.append(String.format("%s (%d), ", drugs.get(i).getName(), drugs.get(i).getStockpile()));
            }
        }
        this.notificationService.create(medicalTherapy.getUser(), content.toString());
    }
}
