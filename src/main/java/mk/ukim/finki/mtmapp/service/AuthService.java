package mk.ukim.finki.mtmapp.service;

import mk.ukim.finki.mtmapp.model.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String username, String password);

    User register(String name, String surname, String username, String password, String repeatedPassword, String email,
                  Integer age);
}
