package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.UserRepository;
import mk.ukim.finki.mtmapp.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Invalid username or password!");
        }
        log.info("Logging in user with username: {}", username);
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User register(String name, String surname, String username, String password, String repeatedPassword, String email, Integer age) {
        if (!password.equals(repeatedPassword)) {
            throw new RuntimeException("Password mismatch!");
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        log.info("Registering new user with username: {}", username);
        return this.userRepository.save(new User(name, surname, username, password, email, age));
    }
}
