package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
