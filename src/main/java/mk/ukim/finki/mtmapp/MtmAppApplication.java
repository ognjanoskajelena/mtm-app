package mk.ukim.finki.mtmapp;

import mk.ukim.finki.mtmapp.model.User;
import mk.ukim.finki.mtmapp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MtmAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(MtmAppApplication.class, args);

        // User
        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);

        User test = new User("Test", "Test", "test", "tt",
                "test@test.com", 45);
        userRepository.save(test);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
