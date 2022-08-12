package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.Notification;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.NotificationRepository;
import mk.ukim.finki.mtmapp.service.NotificationService;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Override
    public Notification findById(Long id) {
        return this.notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Notification with id: %d not found!", id)));
    }

    @Override
    public List<Notification> getAllForUser(String username) {
        Optional<User> optionalUser = this.userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            log.info("Getting all notifications for user with username: {}", username);
            return this.notificationRepository.findAllByOwner(optionalUser.get());
        }
        throw new RuntimeException(String.format("User with username: %s not found!", username));
    }

    @Override
    public Notification create(User owner, String content) {
        log.info("Saving new notification for user with id: {}", owner.getId());
        return this.notificationRepository.save(new Notification(owner, content));
    }

    @Override
    public void see(String username) {
        List<Notification> notifications = this.getAllForUser(username);
        log.info("Seeing notifications for user with username: {}", username);
        for (Notification n: notifications) {
            n.setSeen(true);
            this.notificationRepository.save(n);
        }
    }
}
