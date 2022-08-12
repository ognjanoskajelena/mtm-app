package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import mk.ukim.finki.mtmapp.model.SideEffect;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.model.enums.Use;
import mk.ukim.finki.mtmapp.repository.DrugRepository;
import mk.ukim.finki.mtmapp.repository.MedicalTherapyRepository;
import mk.ukim.finki.mtmapp.service.DrugService;
import mk.ukim.finki.mtmapp.service.NotificationService;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final MedicalTherapyRepository medicalTherapyRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public Optional<Drug> findById(Long id) {
        log.info("Getting drug by id: {}", id);
        return this.drugRepository.findById(id);
    }

    @Override
    public Drug save(String name, Integer dose, Use use, Integer stockpile, Long therapyId) {
        Optional<MedicalTherapy> medicalTherapy = this.medicalTherapyRepository.findById(therapyId);
        if (medicalTherapy.isPresent()) {
            Drug drug = new Drug(name, dose, use, stockpile, medicalTherapy.get());
            log.info("Saving new drug for therapy with id: {}", therapyId);
            return this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", therapyId));
    }

    @Override
    public Drug save(String name, Integer dose, Use use, Integer stockpile, Collection<String> sideEffects, Long therapyId) {
        Optional<MedicalTherapy> medicalTherapy = this.medicalTherapyRepository.findById(therapyId);
        if (medicalTherapy.isPresent()) {
            Drug drug = new Drug(name, dose, use, stockpile, medicalTherapy.get()); // medical therapy - all set
            for (String sideEffect: sideEffects) {
                drug.addSideEffect(new SideEffect(sideEffect, drug));
            }
            log.info("Saving new drug for therapy with id: {}", therapyId);
            return this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Medical therapy with id: %d not found!", therapyId));
    }

    @Override
    public Drug update(Long id, String name, Integer dose, Use use, Integer stockpile) {
        Optional<Drug> optionalDrug = this.drugRepository.findById(id);
        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.setName(name);
            drug.setDose(dose);
            drug.setUse(use);
            drug.setStockpile(stockpile);
            log.info("Updating drug by id: {}", id);
            return this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", id));
    }

    @Override
    public void deleteById(Long id) {
        this.drugRepository.deleteById(id);
    }

    @Override
    public Drug addSideEffect(SideEffect sideEffect, Long drugId) {
        Optional<Drug> optionalDrug = this.drugRepository.findById(drugId);
        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.addSideEffect(sideEffect);
            log.info("Adding side effect for drug with id: {}", drug.getId());
            return this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", drugId));
    }

    @Override
    public Drug removeSideEffect(SideEffect sideEffect, Long drugId) {
        Optional<Drug> optionalDrug = this.drugRepository.findById(drugId);
        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.removeSideEffect(sideEffect);
            log.info("Removing side effect for drug with id: {}", drug.getId());
            return this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", drugId));
    }

    @Override
    public void removeAllSideEffects(Long id) {
        Optional<Drug> optionalDrug = this.findById(id);
        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.getSideEffects().clear();
            this.drugRepository.save(drug);
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", id));
    }

    @Override
    public void getDrug(Long id, String username) {
        Optional<Drug> optionalDrug = this.findById(id);
        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.decreaseStockpile();
            if ((drug.getStockpile() - drug.getDose()) <= drug.getDose()) {
                Optional<User> optionalUser = this.userService.findByUsername(username);
                if (optionalUser.isPresent()) {
                    User owner = optionalUser.get();
                    String content = String.format("Dear %s, you need to refill your stock for drug: %s.",
                            owner.getFullName(), drug.getName());
                    this.notificationService.create(owner, content);
                } else {
                    throw new RuntimeException(String.format("User with username: %s not found!", username));
                }
            }
            this.drugRepository.save(drug);
            return;
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", id));
    }
}
