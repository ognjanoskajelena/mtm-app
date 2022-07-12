package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.MedicalTherapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalTherapyRepository extends JpaRepository<MedicalTherapy, Long> {
}
