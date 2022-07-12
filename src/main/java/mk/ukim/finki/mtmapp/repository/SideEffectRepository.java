package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.SideEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideEffectRepository extends JpaRepository<SideEffect, Long> {
}
