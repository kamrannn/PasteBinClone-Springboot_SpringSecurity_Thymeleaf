package com.app.pastebinclone.service;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.repository.PasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;

    @Autowired
    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    /**
     * This function is getting used to store the paste in the database
     */
    public Paste save(Paste paste) {
        return pasteRepository.save(paste);
    }

    /**
     * This function is getting used to fetch the paste by its id
     */
    public Paste getPasteById(Integer id) {
        Optional<Paste> paste = pasteRepository.findById(id);
        if (paste.isPresent()) {
            return paste.get();
        } else {
            throw new RuntimeException("Paste doesn't exists against this id");
        }
    }

    /**
     * This function is getting used to fetch the list of all those paste who have grant type(passed in parameter)
     */
    public List<Paste> getPasteByGrantType(GrantType type) {
        return pasteRepository.findAllByAuthorizationType(type);
    }
}
