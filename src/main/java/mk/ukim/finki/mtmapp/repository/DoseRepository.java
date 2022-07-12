package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Long> {
}
