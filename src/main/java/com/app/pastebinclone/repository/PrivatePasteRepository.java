package com.app.pastebinclone.repository;

import com.app.pastebinclone.model.PrivatePaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivatePasteRepository extends JpaRepository<PrivatePaste, Integer> {
}
