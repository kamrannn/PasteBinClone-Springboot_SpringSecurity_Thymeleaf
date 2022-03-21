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

    public Paste save(Paste paste) {
        return pasteRepository.save(paste);
    }

    public Paste getPasteById(Integer id) {
        Optional<Paste> paste = pasteRepository.findById(id);
        if (paste.isPresent()) {
            return paste.get();
        } else {
            throw new RuntimeException("Paste doesn't exists against this id");
        }
    }

    public List<Paste> getPasteByGrantType(GrantType type) {
        return pasteRepository.findAllByAuthorizationType(type);
    }
}
