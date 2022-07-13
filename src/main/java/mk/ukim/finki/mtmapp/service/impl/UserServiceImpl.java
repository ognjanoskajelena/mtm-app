package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.UserRepository;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        log.info("Getting user with id: {}", id);
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("Getting user with username: {}", username);
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        log.info("Saving new user");
        return this.userRepository.save(user);
    }
}
