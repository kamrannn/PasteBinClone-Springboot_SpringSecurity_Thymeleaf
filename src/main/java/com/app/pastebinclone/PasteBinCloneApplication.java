package com.app.pastebinclone;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.model.User;
import com.app.pastebinclone.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PasteBinCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteBinCloneApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return args -> {
            User user = new User("Kamran Abbasi", "kamran", "kamran", "kamran");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            Paste paste = new Paste();
            paste.setName("Welcome Java Developer");
            paste.setDescription("A complete description for this paste will come here ");
            paste.setGrant(GrantType.PUBLIC);
            user.getPasteList().add(paste);
            userRepository.save(user);
        };
    }

}
