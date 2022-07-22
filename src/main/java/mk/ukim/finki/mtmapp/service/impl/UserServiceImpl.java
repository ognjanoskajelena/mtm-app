package mk.ukim.finki.mtmapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.UserRepository;
import mk.ukim.finki.mtmapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public User update(Long id, String name, String surname, String username, String password, String email, Integer age) {
        Optional<User> optionalUser = this.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!passwordEncoder.matches(password, user.getPassword()) && !password.isEmpty()) {
                user.setPassword(passwordEncoder.encode(password));
            }
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setEmail(email);
            user.setAge(age);

            return this.userRepository.save(user);
        }
        throw new RuntimeException(String.format("User with id: %d not found!", id));
    }
}
