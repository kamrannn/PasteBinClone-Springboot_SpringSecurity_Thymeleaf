package com.app.pastebinclone.service;

import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.model.PrivatePaste;
import com.app.pastebinclone.model.User;
import com.app.pastebinclone.repository.PrivatePasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivatePasteService {
    private final PrivatePasteRepository privatePasteRepository;

    @Autowired
    public PrivatePasteService(PrivatePasteRepository privatePasteRepository) {
        this.privatePasteRepository = privatePasteRepository;
    }

    public PrivatePaste save(PrivatePaste paste) {
        return privatePasteRepository.save(paste);
    }

    public List<PrivatePaste> privatePasteList(String userEmail) {
        List<PrivatePaste> privatePasteList = privatePasteRepository.findAll();
        List<PrivatePaste> specificUsersPaste = new ArrayList<>();
        for (PrivatePaste paste : privatePasteList
        ) {
            for (User user : paste.getUserList()
            ) {
                if (user.getEmail().equals(userEmail)) {
                    specificUsersPaste.add(paste);
                }
            }
        }
        return specificUsersPaste;
    }

    public PrivatePaste getPasteById(Integer privatePasteId) {
        return privatePasteRepository.getById(privatePasteId);
    }
}
