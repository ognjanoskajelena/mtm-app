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
}
