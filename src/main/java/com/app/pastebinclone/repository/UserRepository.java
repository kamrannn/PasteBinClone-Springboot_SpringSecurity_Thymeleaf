package com.app.pastebinclone.repository;

import com.app.pastebinclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //Fetching the user by its username
    Optional<User> findUserByUsername(String username);

    //Fetching the user by its email
    Optional<User> findUserByEmail(String email);

    //Fetching the user by its username
    User getUserByUsername(String username);
}
