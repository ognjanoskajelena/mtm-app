package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User save(User user);
}
