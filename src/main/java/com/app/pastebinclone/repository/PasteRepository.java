package com.app.pastebinclone.repository;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {
    /**
     * Fetching all the pastes who have grant type which will be paste
     * Whether public or private
     */
    List<Paste> findAllByAuthorizationType(GrantType grantType);
}
