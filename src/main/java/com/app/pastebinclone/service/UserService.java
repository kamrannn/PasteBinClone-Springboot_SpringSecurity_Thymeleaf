package com.app.pastebinclone.service;

import com.app.pastebinclone.model.User;
import com.app.pastebinclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * This encoder is getting used to encrypt the passwords of the users that are getting signed up
     */
    @Qualifier("encoder")
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This function is getting used to save the user in the database
     */
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * This function is getting used by spring security.
     * This is the function which spring security uses to find the user in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = userRepository.getUserByUsername(username);
        if (user1 != null) {
            return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(), new ArrayList<>());
        } else {
            return null;
        }
    }

    /**
     * This function is getting used to user by its username
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * This function is getting used to user by its email
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
