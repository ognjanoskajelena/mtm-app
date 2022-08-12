package mk.ukim.finki.mtmapp.repository;

import mk.ukim.finki.mtmapp.model.Notification;
import mk.ukim.finki.mtmapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOwner(User owner);
}
