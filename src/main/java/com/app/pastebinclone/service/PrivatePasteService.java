package com.app.pastebinclone.service;

import com.app.pastebinclone.model.PrivatePaste;
import com.app.pastebinclone.repository.PrivatePasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivatePasteService {
    private final PrivatePasteRepository privatePasteRepository;

    @Autowired
    public PrivatePasteService(PrivatePasteRepository privatePasteRepository) {
        this.privatePasteRepository = privatePasteRepository;
    }

    public PrivatePaste save(PrivatePaste paste){
        return privatePasteRepository.save(paste);
    }
}
