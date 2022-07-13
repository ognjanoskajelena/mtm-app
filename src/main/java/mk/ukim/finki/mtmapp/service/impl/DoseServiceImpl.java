package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Dose;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.repository.DoseRepository;
import mk.ukim.finki.mtmapp.repository.DrugRepository;
import mk.ukim.finki.mtmapp.service.DoseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DoseServiceImpl implements DoseService {

    private final DoseRepository doseRepository;
    private final DrugRepository drugRepository;

    @Override
    public Optional<Dose> findById(Long id) {
        return this.doseRepository.findById(id);
    }

    @Override
    public Dose save(Integer times, Integer pills, Long drugId) {
        Optional<Drug> drug = this.drugRepository.findById(drugId);
        if (drug.isPresent()) {
            Dose dose = new Dose(times, pills, drug.get());
            log.info("Saving dose for drug with id: {}", drugId);
            return this.doseRepository.save(dose);
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", drugId));
    }

    @Override
    public Dose update(Long id, Integer times, Integer pills) {
        Optional<Dose> optionalDose = this.findById(id);
        if (optionalDose.isPresent()) {
            Dose dose = optionalDose.get();
            dose.setTimes(times);
            dose.setPills(pills);
            log.info("Updating dose by id: {}", id);
            return this.doseRepository.save(dose);
        }
        throw new RuntimeException(String.format("Dose with id: %d not found!", id));
    }

    @Override
    public void deleteById(Long id) {
        this.doseRepository.deleteById(id);
    }

    @Override
    public void deleteByDrugId(Long id) {
        Optional<Drug> drug = this.drugRepository.findById(id);
        if (drug.isPresent()) {
            this.doseRepository.delete(drug.get().getDose());
            log.info("Deleted dose for drug with id: {}", id);
        }
    }
}
