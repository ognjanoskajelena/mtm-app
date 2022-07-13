package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.Dose;

import java.util.Optional;

public interface DoseService {
    Optional<Dose> findById(Long id);

    Dose save(Integer times, Integer pills, Long drugId);

    Dose update(Long id, Integer times, Integer pills);

    void deleteById(Long id);

    void deleteByDrugId(Long id);
}
