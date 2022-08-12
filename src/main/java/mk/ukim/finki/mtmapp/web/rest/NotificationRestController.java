package mk.ukim.finki.mtmapp.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mtmapp.model.Notification;
import mk.ukim.finki.mtmapp.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationRestController {

    private final NotificationService notificationService;

    @GetMapping("/{user}")
    public List<Notification> getAllNotificationsForUser(@PathVariable String user) {
        return this.notificationService.getAllForUser(user);
    }

    @GetMapping("/{user}/seen")
    public void seeNotification(@PathVariable String user) {
        this.notificationService.see(user);
    }
}
