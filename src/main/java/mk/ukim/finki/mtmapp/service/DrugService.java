package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.Drug;
import mk.ukim.finki.mtmapp.model.SideEffect;
import mk.ukim.finki.mtmapp.model.enums.Use;

import java.util.Collection;
import java.util.Optional;

public interface DrugService {
    Optional<Drug> findById(Long id);

    Drug save(String name, Integer dose, Use use, Integer stockpile, Long therapyId);

    Drug save(String name, Integer dose, Use use, Integer stockpile, Collection<String> sideEffects, Long therapyId);

    Drug update(Long id, String name, Integer dose, Use use, Integer stockpile);

    void deleteById(Long id);

    Drug addSideEffect(SideEffect sideEffect, Long drugId);

    Drug removeSideEffect(SideEffect sideEffect, Long drugId);

    void removeAllSideEffects(Long id);

    void getDrug(Long id, String username);
}
