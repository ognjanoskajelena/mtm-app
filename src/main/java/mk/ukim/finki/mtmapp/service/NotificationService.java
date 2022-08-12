package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.Notification;
import mk.ukim.finki.mtmapp.model.User;

import java.util.List;

public interface NotificationService {
    Notification findById(Long id);

    List<Notification> getAllForUser(String username);

    Notification create(User owner, String content);

    void see(String username);
}
