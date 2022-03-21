package com.app.pastebinclone.repository;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {
    List<Paste> findAllByAuthorizationType(GrantType grantType);
}
