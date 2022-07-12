package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
