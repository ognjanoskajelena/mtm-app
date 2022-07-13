package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.SideEffect;
import mk.ukim.finki.mtmapp.repository.DrugRepository;
import mk.ukim.finki.mtmapp.repository.SideEffectRepository;
import mk.ukim.finki.mtmapp.service.SideEffectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SideEffectServiceImpl implements SideEffectService {

    private final SideEffectRepository sideEffectRepository;
    private final DrugRepository drugRepository;

    @Override
    public List<SideEffect> findAll() {
        log.info("Getting all side effects");
        return this.sideEffectRepository.findAll();
    }

    @Override
    public Optional<SideEffect> findById(Long id) {
        log.info("Getting side effect by id: {}", id);
        return this.sideEffectRepository.findById(id);
    }

    @Override
    public SideEffect save(String details, Long drugId) {
        Optional<Drug> drug = this.drugRepository.findById(drugId);
        if (drug.isPresent()) {
            log.info("Saving new side effect for drug with id: {}", drugId);
            return this.sideEffectRepository.save(new SideEffect(details, drug.get()));
        }
        throw new RuntimeException(String.format("Drug with id: %d not found!", drugId));
    }

    @Override
    public void deleteById(Long id) {
        this.sideEffectRepository.deleteById(id);
        log.info("Deleted side effect by id: {}", id);
    }
}
