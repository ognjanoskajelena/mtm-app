package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.SideEffect;

import java.util.List;
import java.util.Optional;

public interface SideEffectService {
    List<SideEffect> findAll();

    Optional<SideEffect> findById(Long id);

    SideEffect save(String details, Long drugId);

    void deleteById(Long id);
}
